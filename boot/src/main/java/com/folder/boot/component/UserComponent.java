package com.folder.boot.component;

import org.springframework.stereotype.Component;

import com.folder.boot.dto.ResponseResult;
import com.folder.boot.dto.User;

@Component
public class UserComponent {

  private ResponseResult result;

  public ResponseResult login(User user) {
    result = new ResponseResult();

    user.setNo(1);
    user.setName("폴더");

    result.setState(true);
    result.setResult(user);
    return result;
  }

  public ResponseResult findById(User user) {
    result = new ResponseResult();

    user.setEmail("user@email.com");
    user.setPwd("1234");
    user.setFileNo(1);

    result.setState(true);
    result.setResult(user);
    return result;
  }

  public ResponseResult editById(User user) {
    result = new ResponseResult();

    result.setState(true);
    result.setResult(user);
    return result;
  }

  public ResponseResult delteById(int no) {
    result = new ResponseResult();

    result.setState(true);
    return result;
  }

  public ResponseResult save(User user) {
    result = new ResponseResult();

    user.setNo(2);

    result.setState(true);
    result.setResult(user);
    return result;
  }

}
