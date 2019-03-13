package com.tenjak.controller.impl;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.concurrent.TimeUnit;

@Configuration
public class caffeineConfig {

    @Bean(name = "manualCache")
    public Cache<String, Object> getManualCache1(){
        Cache<String, Object> manualCache = Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.DAYS)
                .maximumSize(10_000)
                .build();
        return manualCache;
    };
}
