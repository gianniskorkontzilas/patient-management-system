package com.example.patientservice.service;

import com.example.patientservice.entity.Patient;
import com.example.patientservice.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    public Patient updatePatient(Long id, Patient patientDetails) {
        return patientRepository.findById(id)
                .map(patient -> {
                    patient.setFirstname(patientDetails.getFirstname());
                    patient.setLastname(patientDetails.getLastname());
                    patient.setEmail(patientDetails.getEmail());
                    patient.setPhone(patientDetails.getPhone());
                    return patientRepository.save(patient);
                }).orElseThrow(() -> new RuntimeException("Patient not found with id " + id));
    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}
