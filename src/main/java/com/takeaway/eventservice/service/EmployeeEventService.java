package com.takeaway.eventservice.service;

import com.takeaway.eventservice.domain.EmployeeEvent;
import com.takeaway.eventservice.payload.EmployeeEventPayload;
import org.springframework.util.concurrent.ListenableFuture;

public interface EmployeeEventService {

    //Save async
    ListenableFuture<EmployeeEvent> save(EmployeeEventPayload payload);


}
