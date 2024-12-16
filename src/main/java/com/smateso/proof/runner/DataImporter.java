package com.smateso.proof.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

@Component
public class DataImporter implements CommandLineRunner {

    private final CacheManager cacheManager;

    public DataImporter(final CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Override
    public void run(String... args) {
        cacheManager.getCacheNames().forEach(cacheName -> cacheManager.getCache(cacheName).clear());
    }

}
