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
public class File {

  private int no;
  private String name;
  private String extension;
  private String url;
  private String saveName;
  private String savePath;
  private boolean del;
  private LocalDateTime regDate;
  private LocalDateTime alterDate;

}
