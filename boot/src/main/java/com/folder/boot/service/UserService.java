package com.folder.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import com.folder.boot.dto.ResponseResult;
import com.folder.boot.dto.User;
import com.folder.boot.security.TokenGenerator;

import jakarta.servlet.http.HttpServletRequest;

import com.folder.boot.dao.UserDao;

@Service
public class UserService {

  @Autowired UserDao userDao;
  @Autowired TokenGenerator tokenGenerator;
  private ResponseResult responseResult;

  public ResponseResult login(User user) {
    responseResult = new ResponseResult();
    responseResult.setState(false);
    user = userDao.login(user);
    if(user != null) {
      //responseResult.setResult(user);
      ResponseResult tokenResult = tokenGenerator.setJwtToken(user);
      if(tokenResult.isState()) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("no", user.getNo());
        resultMap.put("name", user.getName());
        resultMap.put("token", tokenResult.getResult());
        responseResult.setResult(resultMap);
        responseResult.setState(true);
      }
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

  public ResponseResult findById(HttpServletRequest request){
    responseResult = new ResponseResult();
    responseResult.setState(false);
    ResponseResult tokenResult = tokenGenerator.getJwtInfo(request);
    if(tokenResult.isState()){
      User user = (User) tokenResult.getResult();
      user = userDao.findById(user);
      if(user != null) {
        responseResult.setState(true);
        responseResult.setResult(user);
      }
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

  public ResponseResult editById(User user, HttpServletRequest request) {
    responseResult = new ResponseResult();
    responseResult.setState(false);
    ResponseResult tokenResult = tokenGenerator.getJwtInfo(request);
    if(tokenResult.isState()){
      User tokenUser = (User) tokenResult.getResult();
      if(tokenUser.getNo() == user.getNo() && tokenUser.getEmail().equals(user.getEmail())) {
        int state = userDao.editById(user);
        if(state == 1) {
          responseResult.setState(true);
          responseResult.setMessage("사용자 수정이 성공 하였습니다.");
        } else {
          responseResult.setState(false);
          responseResult.setMessage("사용자 수정이 실패 하였습니다.");
        }
      }
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

  public ResponseResult deleteById(HttpServletRequest request) {
    responseResult = new ResponseResult();
    responseResult.setState(false);
    ResponseResult tokenResult = tokenGenerator.getJwtInfo(request);
    if(tokenResult.isState()){
      User user = (User) tokenResult.getResult();
      int state = userDao.deleteById(user.getNo());
      if(state == 1) {
        responseResult.setState(true);
        responseResult.setMessage("사용자 삭제가 성공 하였습니다.");
      } else {
        responseResult.setState(false);
        responseResult.setMessage("사용자 삭제가 실패 하였습니다.");
      }
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
