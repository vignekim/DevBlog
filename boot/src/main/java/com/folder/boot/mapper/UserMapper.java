package com.folder.boot.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.folder.boot.dto.User;

@Mapper
public interface UserMapper {

  public User login(User user);
  public User findById(User user);
  public int editById(User user);
  public int deleteById(int no);
  public int save(User user);

}
