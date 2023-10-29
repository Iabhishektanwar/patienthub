package com.hospital.patienthub.patienthub.Dao;

import com.hospital.patienthub.patienthub.Entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    public List<Patient> findByPatientName(String patientName);

    @Query(value = "SELECT * FROM patient p WHERE JSON_EXTRACT(p.patient_information, '$.phoneNumber') = ?1",
            nativeQuery = true)
    public List<Patient> findByPhoneNumber(String phoneNumber);
}
