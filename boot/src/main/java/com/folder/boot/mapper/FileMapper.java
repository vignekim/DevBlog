package com.folder.boot.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.folder.boot.dto.FileDto;
import com.folder.boot.dto.User;

@Mapper
public interface FileMapper {

  public int userFile(FileDto file);
  public int user(User user);
  public FileDto userById(int no);

}
