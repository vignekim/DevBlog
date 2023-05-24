package com.folder.boot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.folder.boot.dto.Notice;

@Mapper
public interface NoticeMapper {

  public int save(Notice notice);
  public List<Notice> findByAll();
  public Notice findById(Notice notice);
  public int editById(Notice notice);
  public int deleteById(int no);
  public int cnt(Notice notice);

}
