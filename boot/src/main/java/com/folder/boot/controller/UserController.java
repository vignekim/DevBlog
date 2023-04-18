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

import com.folder.boot.component.UserComponent;
import com.folder.boot.dto.ResponseResult;
import com.folder.boot.dto.User;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/User")
public class UserController {

  @Autowired UserComponent userComponent;

  @PostMapping("/Login")
  public ResponseResult login(@RequestBody User user) {
    return userComponent.login(user);
  }

  @PostMapping("/findById")
  public ResponseResult findById(@RequestBody User user) {
    return userComponent.findById(user);
  }

  @PostMapping("/editById")
  public ResponseResult editById(@RequestBody User user) {
    return userComponent.editById(user);
  }

  @DeleteMapping("/delteById")
  public ResponseResult delteById(@RequestParam("no") int no) {
    return userComponent.delteById(no);
  }

  @PutMapping("/save")
  public ResponseResult save(@RequestBody User user) {
    return userComponent.save(user);
  }

}
