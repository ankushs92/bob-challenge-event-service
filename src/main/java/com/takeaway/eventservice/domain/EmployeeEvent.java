package com.takeaway.eventservice.domain;

import com.takeaway.eventservice.domain.enums.CrudOp;
import com.takeaway.eventservice.jpa.CrudOpJpaConverter;
import com.takeaway.eventservice.payload.EmployeeEventPayload;
import com.takeaway.eventservice.util.Assert;
import com.takeaway.eventservice.util.Json;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

@Table(name = "employees_events")
@Entity
public class EmployeeEvent {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(columnDefinition = "VARCHAR(50)")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "employee_id", columnDefinition = "VARCHAR(50)")
    private UUID employeeId;

    @Column(name = "op", nullable = false, columnDefinition = "TINYINT")
    @Convert(converter = CrudOpJpaConverter.class)
    private CrudOp crudOp;

    @Column(name = "created")
    private ZonedDateTime created;

    @Column(name = "payload", columnDefinition = "json")
    private EmployeeEventPayload payload;

    public EmployeeEvent() {}

    public EmployeeEvent(final EmployeeEventPayload payload) {
        Assert.notNull(payload, "payload cannot be null or empty");
        this.created = ZonedDateTime.now(ZoneId.of("UTC"));
        this.crudOp = payload.getCrudOp();
        this.payload = payload;
        this.employeeId = payload.getId();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public CrudOp getCrudOp() {
        return crudOp;
    }

    public void setCrudOp(CrudOp crudOp) {
        this.crudOp = crudOp;
    }

    public ZonedDateTime getCreated() {
        return created;
    }

    public void setCreated(ZonedDateTime created) {
        this.created = created;
    }

    public EmployeeEventPayload getPayload() {
        return payload;
    }

    public void setPayload(EmployeeEventPayload payload) {
        this.payload = payload;
    }

    public UUID getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(UUID employeeId) {
        this.employeeId = employeeId;
    }
}

