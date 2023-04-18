package com.folder.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.folder.boot.dao.FileDao;

@Service
public class FileService {

  @Autowired FileDao fileDao;

}
