package com.folder.boot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.folder.boot.dto.FileDto;
import com.folder.boot.dto.User;
import com.folder.boot.mapper.FileMapper;

@Repository
public class FileDao {

  @Autowired FileMapper fileMapper;

  public FileDto userFile(FileDto file) {
    int state = fileMapper.userFile(file);
    if(state == 0) {
      file.setNo(0);
    }
    return file;
  }

  public int user(User user) {
    return fileMapper.user(user);
  }

  public FileDto userById(int no) {
    return fileMapper.userById(no);
  }

}
