package com.folder.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.folder.boot.component.FileComponent;
import com.folder.boot.dao.FileDao;
import com.folder.boot.dto.ResponseResult;

@Service
public class FileService {

  @Autowired FileDao fileDao;

  @Autowired FileComponent fileComponent;

  public ResponseResult user(MultipartFile multipartFile) {
    ResponseResult responseResult = new ResponseResult();
    responseResult.setState(false);



    return responseResult;
  }

}
