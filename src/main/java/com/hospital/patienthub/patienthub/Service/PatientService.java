package com.hospital.patienthub.patienthub.Service;

import com.hospital.patienthub.patienthub.Entity.Patient;

import java.util.List;
import java.util.UUID;

public interface PatientService {
    public List<Patient> getAllPatients();
    public Patient getPatientById(long id);
    public Patient insertPatient(Patient patient);
    public void deletePatient(long id);
    public void updatePatient(Patient patient);
}
