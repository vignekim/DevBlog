package com.folder.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.folder.boot.dto.EditorResult;
import com.folder.boot.dto.ResponseResult;
import com.folder.boot.component.FileComponent;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/FileUpload")
public class FileController {

  @Autowired FileComponent fileComponent;

  @GetMapping("/User")
  public ResponseEntity<?> user(@RequestParam("url") String url, @RequestParam("mediaType") String mediaType) {
    return fileComponent.user(url, mediaType);
  }

  @PostMapping("/User")
  public ResponseResult user(@RequestParam("file") MultipartFile multipartFile) {
    return fileComponent.user(multipartFile);
  }

  @GetMapping("/Editor/{fileNo}")
  public ResponseEntity<?> editor(@PathVariable("fileNo") String fileNo) {
    return fileComponent.editor(fileNo);
  }

  @PostMapping("/Editor")
  public EditorResult editor(@RequestParam("file") MultipartFile multipartFile) {
    return fileComponent.editor(multipartFile);
  }

}
