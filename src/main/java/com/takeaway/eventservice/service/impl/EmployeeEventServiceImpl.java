package com.takeaway.eventservice.service.impl;

import com.takeaway.eventservice.domain.EmployeeEvent;
import com.takeaway.eventservice.payload.EmployeeEventPayload;
import com.takeaway.eventservice.repository.EmployeeEventRepository;
import com.takeaway.eventservice.service.EmployeeEventService;
import com.takeaway.eventservice.util.Assert;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class EmployeeEventServiceImpl implements EmployeeEventService {

    private final EmployeeEventRepository repository;

    EmployeeEventServiceImpl(final EmployeeEventRepository repository) {
        this.repository = repository;
    }

    @Override
    @Async
    public ListenableFuture<EmployeeEvent> save(final EmployeeEventPayload payload) {
        Assert.notNull(payload, "EmployeeEventPayload add cannot be null");
        return new AsyncResult<>(repository.save(new EmployeeEvent(payload)));
    }

    @Override
    public Optional<List<EmployeeEvent>> findAllAscendingByCreationTime(final UUID employeeId) {
        Assert.notNull(employeeId, "employeeId add cannot be null");
        return repository.findAllByEmployeeIdOrderByCreatedAsc(employeeId);
    }

}
