package ru.ra1zer.elibrary.Configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StaticResourceConfiguration implements WebMvcConfigurer {
    @Value("${upload.path}")
    private String uploadPath;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) { // меняет адрес с /img/** на uploadPath для картинок
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file:///" + uploadPath);
        registry.addResourceHandler("/content/**")
                .addResourceLocations("file:///D:/Java/Projects/library/src/main/uploads/static/content/pdf/");
    }
}