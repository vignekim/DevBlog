package com.folder.boot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.folder.boot.mapper.UserMapper;

@Repository
public class UserDao {

  @Autowired UserMapper userMapper;

}
