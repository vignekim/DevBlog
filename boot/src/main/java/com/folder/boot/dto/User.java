package com.folder.boot.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class User {

  private int no;
  private String name;
  private String email;
  private String pwd;
  private boolean del;
  private LocalDateTime joinDate;
  private LocalDateTime alterDate;
  private int fileNo;

}
