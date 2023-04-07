package com.springboot.location.openstreetmap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

@SpringBootApplication
public class OpenStreetMapApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenStreetMapApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("geoObjects");
    }
}
