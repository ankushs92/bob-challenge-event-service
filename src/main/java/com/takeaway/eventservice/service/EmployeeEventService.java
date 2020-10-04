package com.takeaway.eventservice.service;

import com.takeaway.eventservice.domain.EmployeeEvent;
import com.takeaway.eventservice.payload.EmployeeEventPayload;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmployeeEventService {

    //Save async
    ListenableFuture<EmployeeEvent> save(EmployeeEventPayload payload);

    Optional<List<EmployeeEvent>> findAllAscendingByCreationTime(UUID employeeId);

}
