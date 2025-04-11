package com.physical.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        
        // 明确指定允许的源
        config.addAllowedOrigin("http://localhost:3000");
        config.addAllowedOrigin("http://192.168.203.240:3000");
        
        // 允许跨域的请求头
        config.addAllowedHeader("*");
        // 允许跨域的请求方法
        config.addAllowedMethod("*");
        // 允许cookie
        config.setAllowCredentials(true);
        // 暴露响应头
        config.addExposedHeader("*");
        
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}