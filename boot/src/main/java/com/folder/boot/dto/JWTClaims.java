package com.folder.boot.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JWTClaims {

  private String issuer;
  private String subject;
  private User audience;
  private Date expiration;
  private Date iIssuedAt;

}
