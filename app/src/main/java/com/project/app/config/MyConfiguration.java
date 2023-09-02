package com.project.app.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@ConfigurationProperties(prefix = "self.conf")
@Configuration
@Data
public class MyConfiguration {

    private String secKey;
    private Duration expiresTime;
    private String[] ignoreUrl;


    private String filePath;
    private String fileAccessPath;
    private String fileApi;


}
