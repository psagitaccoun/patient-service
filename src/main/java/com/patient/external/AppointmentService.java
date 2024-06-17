package com.patient.external;

import com.patient.payload.Appointment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="APPOINTMENT-SERVICE")
public interface AppointmentService {

    @GetMapping("/appointments/patient/{id}")
    public List<Appointment> getOne(@PathVariable String id);
}
