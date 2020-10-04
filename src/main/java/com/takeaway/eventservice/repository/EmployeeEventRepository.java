package com.takeaway.eventservice.repository;

import com.takeaway.eventservice.domain.EmployeeEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmployeeEventRepository extends JpaRepository<EmployeeEvent, UUID> {


}
