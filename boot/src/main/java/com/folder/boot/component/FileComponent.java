package com.folder.boot.component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.folder.boot.dto.EditorResult;
import com.folder.boot.dto.ResponseResult;
import com.folder.boot.dto.EditorFile;

@Component
public class FileComponent {

  private Map<String, Object> resultMap;
  private String baseUrl = "http://localhost:8080";
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

  public ResponseEntity<?> user(String url, String mediaType) {
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

  public ResponseResult user(MultipartFile multipartFile) {
    ResponseResult result = new ResponseResult();
    result.setState(false);

    String name = setName();
    String url = lastPath.concat("/User").concat("/").concat(name);
    String newPath = getRootPath().concat(url);

    if(!multipartFile.isEmpty()){
      File file = new File(newPath);
      if(!file.exists()){file.mkdirs();}
      try {
        multipartFile.transferTo(file);

        resultMap = new HashMap<String, Object>();
        resultMap.put("url", name);
        resultMap.put("mediaType", multipartFile.getContentType());

        result.setState(true);
        result.setResult(resultMap);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return result;
  }

  public ResponseEntity<?> editor(String fileNo) {
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

  public EditorResult editor(MultipartFile multipartFile) {
    /*
    Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("success", 0);
    */
    EditorResult editorResult = new EditorResult();
    editorResult.setSuccess(0);

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
        /*
        Map<String, Object> urlMap = new HashMap<String, Object>();
        urlMap.put("url", "http://localhost:8080/FileUpload/Editor/" + name);
        urlMap.put("name", name + ext);
        urlMap.put("extension", extension);
        urlMap.put("size", getFileSize(multipartFile));

        resultMap.put("success", 1);
        resultMap.put("file", urlMap);
        */
        EditorFile editorFile = new EditorFile();
        editorFile.setUrl(baseUrl + "/FileUpload/Editor/" + name);
        editorFile.setName(name + ext);
        editorFile.setExtension(extension);
        editorFile.setSize(getFileSize(multipartFile));

        editorResult.setSuccess(1);
        editorResult.setFile(editorFile);

      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    //return resultMap;
		return editorResult;
  }

}
