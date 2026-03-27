/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package com.smateso.proof.adapter;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Objects.requireNonNull;

/**
 * The StartUpRunner class is a Spring Component that implements the CommandLineRunner interface.
 * It serves as an entry point for executing custom logic immediately after application startup.
 * This class is responsible for clearing multiple cache entries to ensure that the application
 * starts with fresh and consistent data.
 */
@Slf4j
@Component
@Profile({"dev", "prod", "debug"})
public class StartUpRunner implements CommandLineRunner {

    private final CacheManager cacheManager;

    public StartUpRunner(final @NonNull CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Override
    @Transactional
    public void run(final String... args) {
        this.evictAllCaches();
    }

    public void evictAllCaches() {
        this.cacheManager.getCacheNames().forEach(cacheName -> requireNonNull(cacheManager.getCache(cacheName)).clear());
    }

}
