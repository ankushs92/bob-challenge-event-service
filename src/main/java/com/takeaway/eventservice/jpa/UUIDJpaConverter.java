package com.takeaway.eventservice.jpa;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.UUID;

@Converter(autoApply = true)
public class UUIDJpaConverter implements AttributeConverter<UUID, String> {

    @Override
    public String convertToDatabaseColumn(final UUID uuid) {
        return uuid.toString();
    }

    @Override
    public UUID convertToEntityAttribute(final String dbData) {
        return UUID.fromString(dbData);
    }

}
