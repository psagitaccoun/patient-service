package com.patient.controller;

// PatientController class
import com.patient.entity.Patient;
import com.patient.payload.Doctor;
import com.patient.service.PatientService;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable String id) {
        return patientService.findById(id);
    }

    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.findAll();
    }

    @PostMapping
    public Patient addPatient(@RequestBody Patient patient) {
        return patientService.save(patient);
    }

    @PostMapping("/{patientId}/doctor")
    public ResponseEntity<String> updateDoctor(@PathVariable String patientId,@RequestBody Doctor doctor){
        String s = patientService.updateDoctor(patientId, doctor);
        return  ResponseEntity.ok(s);
    }

    @DeleteMapping("/{id}")
    public void deletePatientById(@PathVariable String id) {
        patientService.deleteById(id);
    }


    @PutMapping("/{id}")
    public Patient updatePatient(@PathVariable String id, @RequestBody Patient patient){
        Patient patient1 = patientService.updatePatient(id, patient);
        return patient1;
    }


    @GetMapping("/pagination")
    public ResponseEntity<Page<Patient>> getAllpatients(Pageable pageable){
        Page<Patient> allpages = patientService.getAllpages(pageable);
        return ResponseEntity.ok().body(allpages);
    }

    // Add more endpoints as needed
}
