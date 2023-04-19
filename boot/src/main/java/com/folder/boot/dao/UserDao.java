package com.folder.boot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.folder.boot.dto.User;
import com.folder.boot.mapper.UserMapper;

@Repository
public class UserDao {

  @Autowired UserMapper userMapper;

  public User login(User user) {
    return userMapper.login(user);
  }

  public User findById(User user) {
    return userMapper.findById(user);
  }

  public int editById(User user) {
    return userMapper.editById(user);
  }

  public int deleteById(int no) {
    return userMapper.deleteById(no);
  }

  public User save(User user) {
    int state = userMapper.save(user);
    if(state == 0) {
      user.setNo(0);
    }
    return user;
  }

}
