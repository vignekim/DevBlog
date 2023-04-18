package com.folder.boot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.folder.boot.mapper.NoticeMapper;

@Repository
public class NoticeDao {

  @Autowired NoticeMapper noticeMapper;

}
