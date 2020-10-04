package com.takeaway.eventservice.jpa;

import com.takeaway.eventservice.domain.enums.CrudOp;

import javax.persistence.AttributeConverter;

public class CrudOpJpaConverter implements AttributeConverter<CrudOp, Integer> {

    @Override
    public Integer convertToDatabaseColumn(final CrudOp crudOp) {
        return crudOp.getCode();
    }

    @Override
    public CrudOp convertToEntityAttribute(final Integer code) {
        return CrudOp.from(code);
    }
}
