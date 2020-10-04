package com.takeaway.eventservice.kafka;


import com.takeaway.eventservice.constants.KafkaTopic;
import com.takeaway.eventservice.payload.EmployeeEventPayload;
import com.takeaway.eventservice.service.EmployeeEventService;
import com.takeaway.eventservice.util.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class KafkaEmployeeEventListener {

    private static final Logger logger = LoggerFactory.getLogger(KafkaEmployeeEventListener.class);
    private final EmployeeEventService employeeEventService;

    KafkaEmployeeEventListener(final EmployeeEventService employeeEventService) {
        this.employeeEventService = employeeEventService;
    }

    //For simplicity, we use all the default features such as default acknowledgement that spring-boot-kafka provides
    //Our listener streams from the Kafka topic and keeps on saving employee events in an async fashion
    //Saving in an async mode enables this consumer to be at pace with the speed of events that are being produced at the producer's end without(possibly) triggering backpressure
    @KafkaListener(topics = KafkaTopic.EMPLOYEE_EVENT)
    public void save(@Payload final String json) {
        logger.debug("Received Employee Event Json {}", json);
        var payload = Json.toObject(json, EmployeeEventPayload.class);
        if (Objects.nonNull(payload)) {
            employeeEventService.save(payload)
                                .addCallback(
                                        (success) -> {},
                                        (failure) -> logger.error("Could not save EmployeeEventPayload {}. Reason :", payload, failure)
                                );
        }
    }

}
