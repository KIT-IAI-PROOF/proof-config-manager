/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package com.smateso.proof.adapter.config.cache;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.cache.RedisCacheConfiguration;

import static java.time.Duration.ofMinutes;
import static java.time.Duration.ofSeconds;

/**
 * Configuration class for Redis caching.
 * This class is responsible for setting up configurations for Redis caching in the application.
 */
@EnableCaching
@Configuration
public class RedisConfig {

    @Bean
    @Profile(value = "prod")
    public RedisCacheConfiguration prodCacheConfiguration() {
        return RedisCacheConfiguration
                .defaultCacheConfig(Thread.currentThread().getContextClassLoader())
                .entryTtl(ofMinutes(10));
    }

    @Bean
    @Profile(value = {"dev", "debug", "test"})
    public RedisCacheConfiguration devCacheConfiguration() {
        return RedisCacheConfiguration
                .defaultCacheConfig(Thread.currentThread().getContextClassLoader())
                .entryTtl(ofSeconds(1));
    }

}