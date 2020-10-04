package com.takeaway.eventservice.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.takeaway.eventservice.domain.enums.CrudOp;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeEventPayload {

    private final UUID id;
    private final String email;
    private final String name;

    private final LocalDate birthday;
    private final DepartmentEventReq department;

    private final ZonedDateTime created;

    private final ZonedDateTime updated;

    private final CrudOp crudOp;

    public EmployeeEventPayload(
            @JsonProperty("id") final UUID id,
            @JsonProperty("email") final String email,
            @JsonProperty("name") final String name,
            @JsonProperty("birthday") final LocalDate birthday,
            @JsonProperty("department") final DepartmentEventReq department,
            @JsonProperty("created") final ZonedDateTime created,
            @JsonProperty("updated") final ZonedDateTime updated,
            @JsonProperty(value = "crudOp", access = JsonProperty.Access.WRITE_ONLY) final CrudOp crudOp
    )
    {
        this.id = id;
        this.email = email;
        this.name = name;
        this.birthday = birthday;
        this.department = department;
        this.created = created;
        this.updated = updated;
        this.crudOp = crudOp;
    }

    private static class DepartmentEventReq {
        private final Integer id;
        private final String name;

        public DepartmentEventReq(
                @JsonProperty("id") final Integer id,
                @JsonProperty("name") final String name
         )
        {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public DepartmentEventReq getDepartment() {
        return department;
    }

    public ZonedDateTime getCreated() {
        return created;
    }

    public ZonedDateTime getUpdated() {
        return updated;
    }

    public CrudOp getCrudOp() {
        return crudOp;
    }


}
