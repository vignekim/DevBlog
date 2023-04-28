package com.folder.boot.security;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.folder.boot.dto.JWTClaims;
import com.folder.boot.dto.JWTHeader;
import com.folder.boot.dto.ResponseResult;
import com.folder.boot.dto.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TokenGenerator {

  @Value(value = "${access.auth.header}")
  private String AUTH_HEADER;

  @Value(value = "${access.auth.type}")
  private String AUTH_TYPE;

  @Value(value = "${access.auth.jwt}")
  private String jwtSecretKey;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private KeyUtils keyUtils;

  private String tokenType = "JWT";
  private String algorithm = "HS256";
  private SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

  public ResponseResult setJwtToken(User user) {
    ResponseResult responseResult = new ResponseResult();
    responseResult.setState(false);
    // 데이터베이스에서 사용자 검색 param(email, pwd) -> result(no, name)
    user = User.builder().no(1).name("폴더").build();
    if(user.getNo() > 0) {
      JwtBuilder token = Jwts.builder()
        .setHeader(createHeader())
        .setClaims(createClaims(user))
        .signWith(createSignature(), signatureAlgorithm);
      responseResult.setResult(AUTH_TYPE.concat(" ").concat(token.compact()));
      responseResult.setState(true);
    }
    return responseResult;
  }

  public Map<String, Object> getJwtInfo(String header) {
    Map<String, Object> resultMap = new HashMap<>();
    String token = getTokenFromHeader(header);
    resultMap.put("state", true);
    resultMap.put("header", getHeaderFromToken(token));
    resultMap.put("payload", getUserFromToken(token));
    return resultMap;
  }

  public boolean isValidToken(String header) {
    try {
      String token = getTokenFromHeader(header);
      if(token != null || !token.isEmpty()) {
        Claims claims = getClaimsFormToken(token);
        log.info("============================================");
        log.info("|ExpireTime\t: {}|", claims.getExpiration());
        log.info("|IIssuedTime\t: {}|", claims.getIssuedAt());
        log.info("|RealTime\t: {}|", Calendar.getInstance().getTime());
        log.info("============================================");
        return true;
      }
    } catch (ExpiredJwtException exception) {
      log.info("==============");
      log.error("Token Expired");
    } catch (JwtException exception) {
      log.info("==============");
      log.error("Token Tampered");
    } catch (NullPointerException exception) {
      log.info("==============");
      log.error("Token is null");
    }
    log.info("==============");
    return false;
  }

  private String setContent(Object object) {
    try {
      return objectMapper.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return "";
  }

  private User getContent(String content) {
    try {
      return objectMapper.readValue(content, new TypeReference<User>() {});
    } catch(JsonProcessingException e) {
      e.printStackTrace();
    }
    return null;
  }

  private Map<String, Object> createHeader() {
    Map<String, Object> header = new HashMap<String, Object>();
    header.put("typ", tokenType);
    header.put("alg", algorithm);
    return header;
  }

  private Claims createClaims(User user) {
    return Jwts.claims()
      .setIssuer("DevBlog")
      .setSubject("User")
      .setAudience(keyUtils.encodeContent(setContent(user)))
      .setExpiration(createExpiredDate(5))
      .setIssuedAt(Calendar.getInstance().getTime());
  }

  private Key createSignature() {
    byte[] jwtKeySecretBytes = DatatypeConverter.parseBase64Binary(jwtSecretKey);
    return new SecretKeySpec(jwtKeySecretBytes, signatureAlgorithm.getJcaName());
  }

  private Date createExpiredDate(int interval) {
    Calendar date = Calendar.getInstance();
    date.add(Calendar.MINUTE, interval);
    return date.getTime();
  }

  private String getTokenFromHeader(String header) {
    String[] strArray = header.split(" ");
    if(AUTH_TYPE.equals(strArray[0])) return strArray[1];
    return null;
  }

  private JWTHeader getHeaderFromToken(String token) {
    JwsHeader<?> jwsHeader = Jwts.parserBuilder()
      .setSigningKey(createSignature()).build()
      .parseClaimsJws(token).getHeader();
    return JWTHeader.builder()
      .type(jwsHeader.getType())
      .algorithm(jwsHeader.getAlgorithm())
      .build();
  }

  private Claims getClaimsFormToken(String token) {
    return Jwts.parserBuilder()
      .setSigningKey(createSignature()).build()
      .parseClaimsJws(token).getBody();
  }

  private User getUserFromToken(String token) {
    Claims claims = getClaimsFormToken(token);
    JWTClaims jwtClaims = JWTClaims.builder()
      .issuer(claims.getIssuer())
      .subject(claims.getSubject())
      .audience(getContent(keyUtils.decodeContent(claims.getAudience())))
      .expiration(claims.getExpiration())
      .iIssuedAt(claims.getIssuedAt())
      .build();
      // 데이터베이스에서 사용자 검색 param(no) -> result(*)
      return jwtClaims.getAudience();
  }

}
