package com.study.bbs;
 
import java.io.File;
 
//ck 프로젝트 외부경로 설정 
public class UploadCk {
  public static synchronized String getUploadDir() {
    String path = "";
    if (File.separator.equals("\\")) {
      path = "C:/aistudy/deploy/ckstorage";
      System.out.println("Windows 10: " + path);
 
    } else {
      // System.out.println("Linux");
      path = "/home/ubuntu/deploy/ckstorage/";
    }
 
    return path;
  }
}