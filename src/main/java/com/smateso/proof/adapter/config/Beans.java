package com.smateso.proof.adapter.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.smateso.proof.adapter.helper.LocalDateTimeTypeAdapter;
import com.smateso.proof.adapter.helper.LocalDateTypeAdapter;
import com.smateso.proof.adapter.helper.OffsetDateTimeTypeAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Configuration
public class Beans {

    @Value("${timezone}")
    private String timezone;

    @Bean
    public Gson gson() {
        final var builder = new GsonBuilder();
        return builder
                .serializeSpecialFloatingPointValues()
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter(this.timezone))
                .registerTypeAdapter(OffsetDateTime.class, new OffsetDateTimeTypeAdapter(this.timezone))
                .setPrettyPrinting()
                .create();
    }

}
