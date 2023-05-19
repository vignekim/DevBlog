package com.folder.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.folder.boot.component.FileComponent;
import com.folder.boot.dao.FileDao;
import com.folder.boot.dto.FileDto;
import com.folder.boot.dto.ResponseResult;
import com.folder.boot.dto.User;
import com.folder.boot.security.TokenGenerator;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class FileService {

  @Autowired FileDao fileDao;
  @Autowired FileComponent fileComponent;
  @Autowired TokenGenerator tokenGenerator;

  public ResponseResult user(MultipartFile multipartFile, HttpServletRequest request) {
    ResponseResult responseResult = new ResponseResult();
    responseResult.setState(false);

    ResponseResult tokenResult = tokenGenerator.getJwtInfo(request);
    if(tokenResult.isState()){
      User user = (User) tokenResult.getResult();

      ResponseResult fileResult = fileComponent.userFile(multipartFile);
      if(fileResult.isState()) {
        FileDto file = (FileDto) fileResult.getResult();
        file = fileDao.userFile(file);

        if(file.getNo() > 0) {
          user.setFileNo(file.getNo());
          int state = fileDao.user(user);

          if(state > 0) {
            responseResult.setState(true);
            responseResult.setResult(file);
          }
        }
      }

    }

    return responseResult;
  }

  public ResponseEntity<?> userById(int no) {
    FileDto fileDto = fileDao.userById(no);
    if(fileDto != null) {
      return fileComponent.userById(fileDto);
    }
    return ResponseEntity.notFound().build();
  }

}
