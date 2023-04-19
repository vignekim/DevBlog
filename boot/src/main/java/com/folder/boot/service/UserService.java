package com.folder.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.folder.boot.dto.ResponseResult;
import com.folder.boot.dto.User;
import com.folder.boot.dao.UserDao;

@Service
public class UserService {

  @Autowired UserDao userDao;
  private ResponseResult responseResult;

  public ResponseResult login(User user) {
    responseResult = new ResponseResult();
    user = userDao.login(user);
    if(user != null) {
      responseResult.setState(true);
      responseResult.setResult(user);
    } else {
      responseResult.setState(false);
    }
    return responseResult;
  }

  public ResponseResult findById(User user) {
    responseResult = new ResponseResult();
    user = userDao.findById(user);
    if(user != null) {
      responseResult.setState(true);
      responseResult.setResult(user);
    } else {
      responseResult.setState(false);
    }
    return responseResult;
  }

  public ResponseResult editById(User user) {
    responseResult = new ResponseResult();
    int state = userDao.editById(user);
    if(state == 1) {
      responseResult.setState(true);
      responseResult.setMessage("사용자 수정이 성공 하였습니다.");
    } else {
      responseResult.setState(false);
      responseResult.setMessage("사용자 수정이 실패 하였습니다.");
    }
    return responseResult;
  }

  public ResponseResult deleteById(int no) {
    responseResult = new ResponseResult();
    int state = userDao.deleteById(no);
    if(state == 1) {
      responseResult.setState(true);
      responseResult.setMessage("사용자 삭제가 성공 하였습니다.");
    } else {
      responseResult.setState(false);
      responseResult.setMessage("사용자 삭제가 실패 하였습니다.");
    }
    return responseResult;
  }

  public ResponseResult save(User user) {
    responseResult = new ResponseResult();
    user = userDao.save(user);
    if(user.getNo() > 0) {
      responseResult.setState(true);
      responseResult.setResult(user);
    } else {
      responseResult.setState(false);
    }
    return responseResult;
  }

}
