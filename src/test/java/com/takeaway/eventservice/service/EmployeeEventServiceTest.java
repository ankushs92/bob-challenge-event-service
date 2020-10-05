package com.takeaway.eventservice.service;

import com.takeaway.eventservice.ChallengeApplicationTests;
import com.takeaway.eventservice.domain.enums.CrudOp;
import com.takeaway.eventservice.payload.EmployeeEventPayload;
import com.takeaway.eventservice.repository.EmployeeEventRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class EmployeeEventServiceTest extends ChallengeApplicationTests {

    @Autowired
    private EmployeeEventRepository repository;

    @Autowired
    private EmployeeEventService employeeEventService;


    @Test
    public void testFindByCreationDesc_shouldReturnAllEventsInSortedOrder() throws ExecutionException, InterruptedException {
        var employee1Uid = UUID.randomUUID();
        var employee2Uid = UUID.randomUUID();
        var event1 = build(employee1Uid, CrudOp.ADD);
        var event2 = build(employee1Uid, CrudOp.UPDATE);
        var event3 = build(employee2Uid, CrudOp.ADD);
        var event4 = build(employee1Uid, CrudOp.UPDATE);
        var event5 = build(employee1Uid, CrudOp.DELETE);

        employeeEventService.save(event1).completable().get();
        employeeEventService.save(event2).completable().get();
        employeeEventService.save(event3).completable().get();
        employeeEventService.save(event4).completable().get();
        employeeEventService.save(event5).completable().get();


        var eventsSortedOpt = employeeEventService.findAllAscendingByCreationTime(employee1Uid);

        assertThat(eventsSortedOpt.isPresent(), equalTo(true));

        var eventsSorted = eventsSortedOpt.get();

        assertThat(eventsSorted.get(0).getCrudOp(), equalTo(CrudOp.ADD));
        assertThat(eventsSorted.get(1).getCrudOp(), equalTo(CrudOp.UPDATE));
        assertThat(eventsSorted.get(2).getCrudOp(), equalTo(CrudOp.UPDATE));
        assertThat(eventsSorted.get(3).getCrudOp(), equalTo(CrudOp.DELETE));

    }


    @Test
    public void testThreeEvents_repositoryShouldReturnOnlyTwoEventsWhenQuriedForAllEvents() throws ExecutionException, InterruptedException {
        var event1 = build(UUID.randomUUID(), CrudOp.ADD);
        var future1 = employeeEventService.save(event1).completable();

        var event2 = build(UUID.randomUUID(), CrudOp.ADD);
        var future2 = employeeEventService.save(event2).completable();

        future1.get();
        future2.get();

        var repoEvents = repository.findAll();

        assertThat(repoEvents.size(), equalTo(2));

    }

    private EmployeeEventPayload build(UUID employeeId, CrudOp crudOp) {
        return new EmployeeEventPayload(
                employeeId,
                "johnny@gmail.com",
                "john",
                LocalDate.of(1992, 5, 5),
                new EmployeeEventPayload.DepartmentEventReq(1, "English"),
                ZonedDateTime.now(),
                ZonedDateTime.now(),
                crudOp
        );
    }
}
