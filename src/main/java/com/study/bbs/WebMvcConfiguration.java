package com.study.bbs;
 
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
 

//파일 미리보기 설정
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
 
       registry.addResourceHandler("/ckstorage/files/**")
               .addResourceLocations("file:///"+UploadCk.getUploadDir()+"/files/");
    }
 
}