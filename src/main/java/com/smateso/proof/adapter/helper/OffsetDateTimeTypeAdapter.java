package com.smateso.proof.adapter.helper;

import com.google.gson.*;
import org.springframework.lang.NonNull;

import java.lang.reflect.Type;
import java.time.OffsetDateTime;
import java.time.ZoneId;

import static java.time.Instant.ofEpochSecond;
import static java.time.OffsetDateTime.ofInstant;

public class OffsetDateTimeTypeAdapter implements JsonSerializer<OffsetDateTime>, JsonDeserializer<OffsetDateTime> {

    private final String timezone;

    public OffsetDateTimeTypeAdapter(@NonNull final String timezone) {
        this.timezone = timezone;
    }

    @Override
    public JsonElement serialize(final OffsetDateTime offsetDateTime, final Type srcType, final JsonSerializationContext context) {
        final var instant = offsetDateTime.toInstant();
        return new JsonPrimitive(instant.getEpochSecond());
    }

    @Override
    public OffsetDateTime deserialize(final JsonElement json, final Type type, final JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        final var instant = ofEpochSecond(json.getAsJsonPrimitive().getAsLong());
        return ofInstant(instant, ZoneId.of(this.timezone));
    }

}