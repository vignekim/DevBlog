package com.folder.boot.security;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Objects;

import javax.crypto.Cipher;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class KeyUtils {

  @Value(value = "${access.keys.public}")
  private String KEYS_PUBLIC;

  @Value(value = "${access.keys.private}")
  private String KEYS_PRIVATE;

  @Value(value = "${access.keys.instance}")
  private String KEYS_INSTANCE;

  private KeyPair _accessKeyPair;

  private KeyPair getAccessKeyPair() {
    if( Objects.isNull(_accessKeyPair) ) {
      _accessKeyPair = getKeyPair(KEYS_PUBLIC, KEYS_PRIVATE);
    }
    return _accessKeyPair;
  }

  private KeyPair getKeyPair(String publicKeyPath, String privateKeyPath) {
    File publicKeyFile = new File(publicKeyPath);
    File privateKeyFile = new File(privateKeyPath);

    if( !publicKeyFile.exists() && !privateKeyFile.exists() ) {
      return setAccessKeyPair(publicKeyPath, privateKeyPath);
    }
    log.info("===============================================================================================");
    log.info("|대상 키 주소: 공개키 - {}, 개인키 - {}|", publicKeyFile, privateKeyFile);
    log.info("===============================================================================================");
    try {
      KeyFactory keyFactory = KeyFactory.getInstance(KEYS_INSTANCE);

      byte[] publicKeyBytes = Files.readAllBytes(publicKeyFile.toPath());
      EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
      PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

      byte[] privateKeyBytes = Files.readAllBytes(privateKeyFile.toPath());
      PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
      PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

      return new KeyPair(publicKey, privateKey);
    } catch (NoSuchAlgorithmException | IOException | InvalidKeySpecException e) {
      e.printStackTrace();
    }
    return null;
  }

  private KeyPair setAccessKeyPair(String publicKeyPath, String privateKeyPath) {
    File publicKeyFile = new File(publicKeyPath);
    File privateKeyFile = new File(privateKeyPath);
    File directory = new File("access-keys");
    if (!directory.exists()) {
        directory.mkdirs();
    }
    log.info("===============================================================================================");
    log.info("|신규 키 주소: 공개키 - {}, 개인키 - {}|", publicKeyFile, privateKeyFile);
    log.info("===============================================================================================");
    try {
      SecureRandom secureRandom = new SecureRandom();
      KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEYS_INSTANCE);
      keyPairGenerator.initialize(2048, secureRandom);
      KeyPair keyPair = keyPairGenerator.generateKeyPair();

      try ( FileOutputStream fileOutputStream = new FileOutputStream(publicKeyPath) ) {
        PublicKey publicKeyPair = keyPair.getPublic();
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyPair.getEncoded());
        fileOutputStream.write(keySpec.getEncoded());
      }

      try ( FileOutputStream fileOutputStream = new FileOutputStream(privateKeyPath) ) {
        PrivateKey privateKeyPair = keyPair.getPrivate();
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyPair.getEncoded());
        fileOutputStream.write(keySpec.getEncoded());
      }

      return keyPair;
    } catch (NoSuchAlgorithmException | IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  private Cipher getCipherPublicKey() {
    Cipher cipher = null;
    try {
      cipher = Cipher.getInstance(KEYS_INSTANCE);
		  cipher.init(Cipher.ENCRYPT_MODE, getAccessKeyPair().getPublic());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return cipher;
  };

  private Cipher getCipherPrivateKey() {
    Cipher cipher = null;
    try {
      cipher = Cipher.getInstance(KEYS_INSTANCE);
		  cipher.init(Cipher.DECRYPT_MODE, getAccessKeyPair().getPrivate());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return cipher;
  };

  public String encodeContent(String content) {
    try {
      Cipher cipher = getCipherPublicKey();
      byte[] decryptedBytes = cipher.doFinal(content.getBytes());
		  return Base64.getEncoder().encodeToString(decryptedBytes);
    } catch(Exception e) {
      e.printStackTrace();
    }
    return "";
  }

  public String decodeContent(String content) {
    try {
      Cipher cipher = getCipherPrivateKey();
      byte[] encryptedBytes =  Base64.getDecoder().decode(content);
			byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
      return new String(decryptedBytes, "utf-8");
    } catch(Exception e) {
      e.printStackTrace();
    }
    return "";
  }

}
