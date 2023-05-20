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
import com.folder.boot.service.NoticeService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Notice")
public class NoticeController {

  @Autowired NoticeComponent noticeComponent;
  @Autowired NoticeService noticeService;

  @PostMapping("/List")
  public ResponseResult list() {
    return noticeService.findByAll();
  }

  @PostMapping("/findById")
  public ResponseResult findById(@RequestBody Notice notice) {
    return noticeService.findById(notice);
  }

  @PostMapping("/editById")
  public ResponseResult editById(@RequestBody Notice notice) {
    return noticeService.editById(notice);
  }

  @DeleteMapping("/deleteById")
  public ResponseResult deleteById(@RequestParam("no") int no) {
    return noticeService.deleteById(no);
  }

  @PutMapping("/save")
  public ResponseResult save(@RequestBody Notice notice) {
    return noticeService.save(notice);
  }

}
