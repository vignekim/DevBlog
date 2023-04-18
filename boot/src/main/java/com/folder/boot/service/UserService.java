package com.folder.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.folder.boot.dao.UserDao;

@Service
public class UserService {

  @Autowired UserDao userDao;

}
