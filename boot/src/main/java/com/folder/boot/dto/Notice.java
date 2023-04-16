package com.folder.boot.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Notice {

  private int no;
  private String title;
  private String desc;
  private String content;
  private boolean del;
  private LocalDateTime regDate;
  private LocalDateTime alterDate;
  private int userNo;

}
