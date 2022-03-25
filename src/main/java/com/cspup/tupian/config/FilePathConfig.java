package com.cspup.tupian.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author csp
 * @date 2022/3/25 14:57
 * @description
 */
@Configuration
public class FilePathConfig implements WebMvcConfigurer {

    @Value("${cspup.imgDir}")
    String filePath;

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**").addResourceLocations("file:"+ filePath);
    }
}
