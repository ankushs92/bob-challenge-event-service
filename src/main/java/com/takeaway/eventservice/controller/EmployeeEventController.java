package com.takeaway.eventservice.controller;

import com.takeaway.eventservice.resp.EmployeeEventListResp;
import com.takeaway.eventservice.service.EmployeeEventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.UUID;

@RestController
@RequestMapping("/employee")
public class EmployeeEventController {

    private final EmployeeEventService employeeEventService;

    EmployeeEventController(final EmployeeEventService employeeEventService) {
        this.employeeEventService = employeeEventService;
    }

    @GetMapping(value = "/{uuid}/events", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeEventListResp> findEvents(@PathVariable final String uuid) {
        var employeeId = UUID.fromString(uuid);
        var employeeEvents = employeeEventService.findAllAscendingByCreationTime(employeeId)
                                                 .orElse(Collections.emptyList());

        var resp = new EmployeeEventListResp(employeeEvents);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

}
