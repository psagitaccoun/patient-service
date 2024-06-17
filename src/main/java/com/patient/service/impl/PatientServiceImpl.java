package com.patient.service.impl;

// PatientServiceImpl class
import com.patient.entity.Patient;
import com.patient.external.AppointmentService;
import com.patient.external.DoctorService;
import com.patient.payload.Appointment;
import com.patient.payload.Doctor;
import com.patient.repository.PatientRepository;
import com.patient.service.PatientService;
import jakarta.inject.Singleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorService doctorService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Patient findById(String id) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        Patient patient = optionalPatient.orElse(null);

//        Appointment[] app = restTemplate.getForObject("http://APPOINTMENT-SERVICE/appointments/patient/" + id, Appointment[].class);
//        List<Appointment> list = Arrays.stream(app).toList();
        List<Appointment> one = appointmentService.getOne(id);
        patient.setAppointment(one);
        return patient;
    }

    @Override
    public List<Patient> findAll() {
        List<Patient> all = patientRepository.findAll();
        all.stream().map(n->{
            List<Appointment> one = appointmentService.getOne(n.getId());
            n.setAppointment(one);
            return n;
        }).collect(Collectors.toList());
        return all;
    }

    @Override
    public Patient save(Patient patient) {
        String string = UUID.randomUUID().toString();
        patient.setId(string);
        Patient p1 = patientRepository.save(patient);

        Doctor doctor = patient.getDoctor();

        doctorService.addDoctor(patient.getDoctor());
  
       // Doctor doctor = patient.getDoctor();
     //   restTemplate.postForObject("http://localhost:8082/doctors", doctor, Doctor.class);
     //   restTemplate.postForObject("http://DOCTOR-SERVICE/doctors", doctor, Doctor.class);
        return p1;
    }

    @Override
    public void deleteById(String id) {
        patientRepository.deleteById(id);
    }

    @Override
    public Patient updatePatient(String id, Patient patient) {
        Patient patient1 = patientRepository.findById(id).orElse(null);
        patient1.setAge(patient.getAge());
        patient1.setName(patient.getName());
        patient1.setGender(patient.getGender());
        patient1.setAddress(patient.getAddress());

        Patient save = patientRepository.save(patient1);
        return save;
    }

    @Override
    public String updateDoctor(String patientId, Doctor doctor) {
//        //it will give all appointments for patientId 1
//        Appointment[] forObject = restTemplate.getForObject("http://localhost:8083/appointments/patient/" + patientId, Appointment[].class);
//        List<Appointment> list = Arrays.stream(forObject).toList();
//
//        //for patient 1
//        List<Appointment> collect = list.stream().filter(n -> n.getDoctorId().equals(doctor.getId())).collect(Collectors.toList());
//
      //  String doctorServiceUrl = "http://localhost:8081/doctors/" + updatedDoctor.getId();  

       // String doctorurl = "http://localhost:8082/doctors/" + doctor.getId();
       // restTemplate.put("http://localhost:8082/doctors/" + doctor.getId(), Doctor.class);
        restTemplate.put("http://DOCTOR-SERVICE/doctors/" + doctor.getId(),doctor);

        return "doctor data updated successfully!!";
    }

    @Override
    public Page<Patient> getAllpages(Pageable pageable) {
        Page<Patient> all = patientRepository.findAll(pageable);
        return all;
    }

    // Add more methods as needed
}
