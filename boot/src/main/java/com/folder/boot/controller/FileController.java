package com.folder.boot.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/FileUpload")
public class FileController {

  private Map<String, Object> resultMap;
  private String lastPath = "/upload";
  private String getRootPath() {return new File("").getAbsolutePath();}
  private String getName(MultipartFile file) {return file.getOriginalFilename();}
  private String setName() {return Long.toString(System.nanoTime());}
  private String getExtension(MultipartFile file) {
    String contentType = file.getContentType();
    String name = getName(file);
    String originalFileExtension = "";
    if (!ObjectUtils.isEmpty(contentType)){
        if(contentType.contains("image/jpeg")){originalFileExtension = ".jpg";}
        else if(contentType.contains("image/png")){originalFileExtension = ".png";}
        else if(contentType.contains("image/gif")){originalFileExtension = ".gif";}
        else if(name.lastIndexOf(".") > 0){originalFileExtension = name.substring(name.lastIndexOf("."), name.length());}
    }
    return originalFileExtension;
  }
  private long getFileSize(MultipartFile file) {return file.getSize();}

  @GetMapping("/User")
  public ResponseEntity<?> user(@RequestParam("url") String url, @RequestParam("mediaType") String mediaType) {
    try {
      String path = getRootPath().concat("/upload/User/").concat(url);
      File file = new File(path);
      return ResponseEntity.ok()
        .contentLength(file.length())
        .contentType(MediaType.parseMediaType(mediaType))
        .body(new InputStreamResource(new FileInputStream(file)));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping("/User")
  public Map<String, Object> user(@RequestParam("file") MultipartFile multipartFile) {
    resultMap = new HashMap<String, Object>();
    resultMap.put("state", false);

    String name = setName();
    String url = lastPath.concat("/User").concat("/").concat(name);
    String newPath = getRootPath().concat(url);

    if(!multipartFile.isEmpty()){
      File file = new File(newPath);
      if(!file.exists()){file.mkdirs();}
      try {
        multipartFile.transferTo(file);
        resultMap.put("url", name);
        resultMap.put("state", true);
        resultMap.put("mediaType", multipartFile.getContentType());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return resultMap;
  }



  @GetMapping("/Editor/{fileNo}")
  public ResponseEntity<?> editor(@PathVariable("fileNo") String fileNo) {
    try {
      String path = getRootPath().concat(lastPath).concat("/editor/").concat(fileNo);
      File file = new File(path);
      return ResponseEntity.ok()
        .contentLength(file.length())
        .contentType(MediaType.parseMediaType("image/png"))
        .body(new InputStreamResource(new FileInputStream(file)));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping("/Editor")
  public Map<String, Object> editor(@RequestParam("file") MultipartFile multipartFile) {

    Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("success", 0);

    if(!multipartFile.isEmpty()){
      String name = setName();
      String ext = getExtension(multipartFile);
      String extension = ext.substring(ext.lastIndexOf(".") + 1, ext.length());
      String url = lastPath.concat("/editor").concat("/").concat(name);
      String path = getRootPath().concat(url);
      File file = new File(path);
      if(!file.exists()){
        file.mkdirs();
      }
      try {
        multipartFile.transferTo(file);

        Map<String, Object> urlMap = new HashMap<String, Object>();
        urlMap.put("url", "http://localhost:8080/FileUpload/Editor/" + name);
        urlMap.put("name", name + ext);
        urlMap.put("extension", extension);
        urlMap.put("size", getFileSize(multipartFile));

        resultMap.put("success", 1);
        resultMap.put("file", urlMap);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
		return resultMap;
  }

}
