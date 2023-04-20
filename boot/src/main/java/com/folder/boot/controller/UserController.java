package com.folder.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.folder.boot.dto.ResponseResult;
import com.folder.boot.dto.User;
import com.folder.boot.service.UserService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/User")
public class UserController {

  //@Autowired UserComponent userComponent;
  @Autowired UserService userService;

  @PostMapping("/Login")
  public ResponseResult login(@RequestBody User user) {
    return userService.login(user);
  }

  @PostMapping("/findById")
  public ResponseResult findById(@RequestBody User user) {
    return userService.findById(user);
  }

  @PostMapping("/editById")
  public ResponseResult editById(@RequestBody User user) {
    return userService.editById(user);
  }

  @DeleteMapping("/deleteById")
  public ResponseResult deleteById(@RequestParam("no") int no) {
    return userService.deleteById(no);
  }

  @PutMapping("/save")
  public ResponseResult save(@RequestBody User user) {
    return userService.save(user);
  }

}
