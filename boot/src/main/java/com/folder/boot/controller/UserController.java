package com.folder.boot.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.folder.boot.dto.User;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/User")
public class UserController {

  @PostMapping("/Login")
  public Map<String, Object> login(@RequestBody User user) {
    user.setNo(1);
    user.setName("폴더");
    Map<String, Object> resultMap = new HashMap<String, Object>();
    resultMap.put("state", true);
    resultMap.put("user", user);
    return resultMap;
  }

  @PostMapping("/findById")
  public Map<String, Object> findById(@RequestBody User user) {
    user.setEmail("user@email.com");
    user.setPwd("1234");
    user.setFileNo(1);
    Map<String, Object> resultMap = new HashMap<String, Object>();
    resultMap.put("state", true);
    resultMap.put("user", user);
    return resultMap;
  }

  @PostMapping("/editById")
  public Map<String, Object> editById(@RequestBody User user) {
    Map<String, Object> resultMap = new HashMap<String, Object>();
    resultMap.put("state", true);
    resultMap.put("user", user);
    return resultMap;
  }

  @PostMapping("/delteById")
  public Map<String, Object> delteById(@RequestBody User user) {
    Map<String, Object> resultMap = new HashMap<String, Object>();
    resultMap.put("state", true);
    return resultMap;
  }

  @PostMapping("/save")
  public Map<String, Object> save(@RequestBody User user) {
    user.setNo(2);
    Map<String, Object> resultMap = new HashMap<String, Object>();
    resultMap.put("state", true);
    resultMap.put("user", user);
    return resultMap;
  }

}
