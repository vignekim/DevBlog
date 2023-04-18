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

import com.folder.boot.component.NoticeComponent;
import com.folder.boot.dto.Notice;
import com.folder.boot.dto.ResponseResult;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Notice")
public class NoticeController {

  @Autowired NoticeComponent noticeComponent;

  @PostMapping("/List")
  public ResponseResult list() {
    return noticeComponent.list();
  }

  @PostMapping("/findById")
  public ResponseResult findById(@RequestBody Notice notice) {
    return noticeComponent.findById(notice);
  }

  @PostMapping("/editById")
  public ResponseResult editById(@RequestBody Notice notice) {
    return noticeComponent.result(true);
  }

  @DeleteMapping("/deleteById")
  public ResponseResult deleteById(@RequestParam("no") int no) {
    return noticeComponent.result(true);
  }

  @PutMapping("/save")
  public ResponseResult save(@RequestBody Notice notice) {
    return noticeComponent.save(notice);
  }

}
