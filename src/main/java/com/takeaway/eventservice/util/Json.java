package com.takeaway.eventservice.util;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.takeaway.eventservice.jackson.LocalDateDeserializer;
import com.takeaway.eventservice.jackson.LocalDateSerializer;
import com.takeaway.eventservice.jackson.ZonedDateTimeDeserializer;
import com.takeaway.eventservice.jackson.ZonedDateTimeSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.ZonedDateTime;


public class Json {

    private static final Logger logger = LoggerFactory.getLogger(Json.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        var simpleModule = new SimpleModule();
        simpleModule.addSerializer(LocalDate.class, new LocalDateSerializer());
        simpleModule.addDeserializer(LocalDate.class, new LocalDateDeserializer());
        simpleModule.addSerializer(ZonedDateTime.class, new ZonedDateTimeSerializer());
        simpleModule.addDeserializer(ZonedDateTime.class, new ZonedDateTimeDeserializer());

        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .registerModule(simpleModule);
    }

    public static <T> T toObject(final String json, final Class<T> clazz)  {
        Assert.notNull(clazz, "clazz cannot be null");
        try {
            return objectMapper
                    .readValue(json, clazz);
        } catch (final JsonProcessingException e) {
            logger.error("", e);
            return null;
        }
    }

    public static String encode(final Object object)  {
        Assert.notNull(object, "object cannot be null");
        try {
            return objectMapper
                    .writeValueAsString(object);
        }
        catch (final JsonProcessingException ex) {
            logger.error("", ex);
            return null;
        }
    }



}
