package com.takeaway.eventservice.resp;

import com.takeaway.eventservice.domain.EmployeeEvent;
import com.takeaway.eventservice.domain.enums.CrudOp;
import com.takeaway.eventservice.jpa.CrudOpJpaConverter;
import com.takeaway.eventservice.payload.EmployeeEventPayload;
import com.takeaway.eventservice.util.Assert;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Convert;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class EmployeeEventListResp {

    private final List<EmployeeEventResp> events;

    public EmployeeEventListResp(final List<EmployeeEvent> events) {
        Assert.notNull(events, "List<EmployeeEvent> events cannot be null");
        this.events = events.stream()
                            .map(EmployeeEventResp :: new)
                            .collect(Collectors.toList());
    }

    public List<EmployeeEventResp> getEvents() {
        return events;
    }

    private static class EmployeeEventResp {

        private final UUID id;
        private final UUID employeeId;
        private final CrudOp crudOp;
        private final ZonedDateTime created;
        private final EmployeeEventPayload data;

        EmployeeEventResp(final EmployeeEvent event) {
            Assert.notNull(event, "EmployeeEvent cannot be null");
            this.id = event.getId();
            this.employeeId = event.getEmployeeId();
            this.crudOp = event.getCrudOp();
            this.created = event.getCreated();
            this.data = event.getPayload();
        }

        public UUID getId() {
            return id;
        }

        public UUID getEmployeeId() {
            return employeeId;
        }

        public CrudOp getCrudOp() {
            return crudOp;
        }

        public ZonedDateTime getCreated() {
            return created;
        }

        public EmployeeEventPayload getData() {
            return data;
        }
    }

}
