package com.folder.boot.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.folder.boot.dao.NoticeDao;
import com.folder.boot.dto.Notice;
import com.folder.boot.dto.ResponseResult;
import com.folder.boot.dto.User;
import com.folder.boot.security.TokenGenerator;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class NoticeService {

  @Autowired NoticeDao noticeDao;
  @Autowired TokenGenerator tokenGenerator;
  private ResponseResult responseResult;

  public ResponseResult save(Notice notice, HttpServletRequest request) {
    responseResult = new ResponseResult();
    responseResult.setState(false);
    ResponseResult tokenResult = tokenGenerator.getJwtInfo(request);
    if(tokenResult.isState()){
      User user = (User) tokenResult.getResult();
      notice.setUserNo(user.getNo());
      //notice.setUserNo(1);
      notice = noticeDao.save(notice);
      if(notice.getNo() > 0) {
        responseResult.setState(true);
        responseResult.setResult(notice);
      }
    }
    return responseResult;
  }

  public ResponseResult findByAll() {
    responseResult = new ResponseResult();
    responseResult.setState(true);
    responseResult.setResult(noticeDao.findByAll());
    return responseResult;
  }

  public ResponseResult findById(Notice notice) {
    responseResult = new ResponseResult();
    responseResult.setState(false);
    notice = noticeDao.findById(notice);
    if(notice != null) {

      int state = noticeDao.cnt(notice);
      if(state > 0) {
        responseResult.setState(true);
        responseResult.setResult(notice);
      }

    }
    return responseResult;
  }

  public ResponseResult findById(Notice notice, HttpServletRequest request) {
    responseResult = new ResponseResult();
    responseResult.setState(false);
    notice = noticeDao.findById(notice);
    if(notice != null) {

      ResponseResult tokenResult = tokenGenerator.getJwtInfo(request);
      Notice tokenNotice = new Notice();
      tokenNotice.setNo(notice.getNo());
      if(tokenResult.isState()){
        User user = (User) tokenResult.getResult();
        tokenNotice.setUserNo(user.getNo());
      } else {
        tokenNotice.setUserNo(0);
      }

      int state = noticeDao.cnt(tokenNotice);
      if(state > 0) {
        responseResult.setState(true);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("notice", notice);
        resultMap.put("auth", true);
        if(tokenNotice.getUserNo() > 0) {
          if(tokenNotice.getUserNo() == notice.getUserNo()) {
            resultMap.put("auth", false);
          }
        }
        responseResult.setResult(resultMap);
      }

    }
    return responseResult;
  }

  public ResponseResult editById(Notice notice) {
    responseResult = new ResponseResult();
    int state = noticeDao.editById(notice);
    if(state == 1) {
      responseResult.setState(true);
      responseResult.setResult("게시판 글 작성이 성공 하였습니다.");
    } else {
      responseResult.setState(false);
      responseResult.setResult("게시판 글 작성이 실패 하였습니다.");
    }
    return responseResult;
  }

  public ResponseResult editById(Notice notice, HttpServletRequest request) {
    responseResult = new ResponseResult();

    ResponseResult tokenResult = tokenGenerator.getJwtInfo(request);
    if(tokenResult.isState()){
      User user = (User) tokenResult.getResult();

      if(user.getNo() == notice.getUserNo()) {
        int state = noticeDao.editById(notice);
        if(state == 1) {
          responseResult.setState(true);
          responseResult.setResult("게시판 글 작성이 성공 하였습니다.");
        } else {
          responseResult.setState(false);
          responseResult.setResult("게시판 글 작성이 실패 하였습니다.");
        }
      }

    }

    return responseResult;
  }

  public ResponseResult deleteById(int no) {
    responseResult = new ResponseResult();
    int state = noticeDao.deleteById(no);
    if(state == 1) {
      responseResult.setState(true);
      responseResult.setResult("게시판 글 삭제가 성공 하였습니다.");
    } else {
      responseResult.setState(false);
      responseResult.setResult("게시판 글 삭제가 실패 하였습니다.");
    }
    return responseResult;
  }

}
