package com.folder.boot.dto;

import lombok.Data;

@Data
public class ResponseResult {

  private boolean state;
  private Object result;
  private String message;

}
