package com.example.msghub.common.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class MsgHubConfig {
    @Value("${msghub.apiKey}")
    private String apiKey;

    @Value("${msghub.rawPwd}")
    private String rawPwd;

    @Value("${msghub.baseURL}")
    private String baseUrl;
}
