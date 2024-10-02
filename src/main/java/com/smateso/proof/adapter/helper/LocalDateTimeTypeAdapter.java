package com.smateso.proof.adapter.helper;

import com.google.gson.*;
import org.springframework.lang.NonNull;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static java.time.Instant.ofEpochSecond;
import static java.time.LocalDateTime.ofInstant;

public class LocalDateTimeTypeAdapter implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {

    private final String timezone;

    public LocalDateTimeTypeAdapter(@NonNull final String timezone) {
        this.timezone = timezone;
    }

    @Override
    public JsonElement serialize(final LocalDateTime localDateTime, final Type srcType, final JsonSerializationContext context) {
        final var zoneId = ZoneId.of(this.timezone);
        final var zonedDateTime = localDateTime.atZone(zoneId);
        final var instant = zonedDateTime.toInstant();
        return new JsonPrimitive(instant.getEpochSecond());
    }

    @Override
    public LocalDateTime deserialize(final JsonElement json, final Type type, final JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        final var instant = ofEpochSecond(json.getAsJsonPrimitive().getAsLong());
        return ofInstant(instant, ZoneId.of(this.timezone));
    }

}