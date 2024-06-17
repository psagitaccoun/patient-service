package com.patient.service;

// PatientService interface
import com.patient.entity.Patient;
import com.patient.payload.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PatientService {
    Patient findById(String id);
    List<Patient> findAll();
    Patient save(Patient patient);
    void deleteById(String id);

    Patient updatePatient(String id,Patient patient);

    String updateDoctor(String patientId, Doctor doctor);

    Page<Patient> getAllpages(Pageable pageable);
    // Add more methods as needed
}
