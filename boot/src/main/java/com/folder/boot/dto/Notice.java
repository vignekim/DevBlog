package com.folder.boot.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
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
