package com.patient.entity;

import com.patient.payload.Appointment;
import com.patient.payload.Doctor;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="patients")
public class Patient {
    @Id
    private String id;
    private String name;
    private int age;
    private String gender;
    private String address;

    @Transient
    List<Appointment> appointment;

    @Transient
    Doctor doctor;
}
