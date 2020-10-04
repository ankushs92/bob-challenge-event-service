package com.takeaway.eventservice.jpa;

import com.takeaway.eventservice.payload.EmployeeEventPayload;
import com.takeaway.eventservice.util.Json;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class EmployeeEventPayloadJpaConverter implements AttributeConverter<EmployeeEventPayload, String> {

    @Override
    public String convertToDatabaseColumn(final EmployeeEventPayload payload) {
        return Json.encode(payload);
    }

    @Override
    public EmployeeEventPayload convertToEntityAttribute(final String json) {
        return Json.toObject(json, EmployeeEventPayload.class);
    }

}
