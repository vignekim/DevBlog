package com.folder.boot.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.folder.boot.dto.Notice;
import com.folder.boot.mapper.NoticeMapper;

@Repository
public class NoticeDao {

  @Autowired NoticeMapper noticeMapper;

  public Notice save(Notice notice) {
    int state = noticeMapper.save(notice);
    if(state == 0) {
      notice.setNo(0);
    }
    return notice;
  }

  public List<Notice> findByAll() {
    return noticeMapper.findByAll();
  }

  public Notice findById(Notice notice) {
    return noticeMapper.findById(notice);
  }

  public int editById(Notice notice) {
    return noticeMapper.editById(notice);
  }

  public int deleteById(int no) {
    return noticeMapper.deleteById(no);
  }

  public int cnt(Notice notice) {
    return noticeMapper.cnt(notice);
  }

}
