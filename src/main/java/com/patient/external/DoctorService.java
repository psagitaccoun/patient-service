package com.patient.external;


import com.patient.payload.Doctor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name="DOCTOR-SERVICE")
public interface DoctorService {

    @PostMapping("/doctors")
    public Doctor addDoctor(Doctor doctor);
}
