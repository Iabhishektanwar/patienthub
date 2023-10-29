package com.hospital.patienthub.patienthub.Service;

import com.hospital.patienthub.patienthub.Dao.PatientRepository;
import com.hospital.patienthub.patienthub.Entity.Patient;
import com.hospital.patienthub.patienthub.Exceptions.InvalidIDException;
import com.hospital.patienthub.patienthub.Exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    @Autowired
    private final PatientRepository patientRepository;

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    @Cacheable(cacheNames = "patients", key = "#a0")
    public Patient getPatientById(long id) {
        System.out.println("fetching from db");
        Optional<Patient> optionalPatient = patientRepository.findById(id);

        if (optionalPatient.isPresent()) {
            return optionalPatient.get();
        } else {
            throw new ResourceNotFoundException("Patient not found with ID: " + id);
        }
    }

    @Override
    public Patient insertPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    @CacheEvict(cacheNames = "patients", key = "#a0")
    public void deletePatient(long id) {
        System.out.println("deleting from db and cache");
        Optional<Patient> optionalPatient = patientRepository.findById(id);

        if (optionalPatient.isPresent()) {
            patientRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException();
        }
    }

    @Override
    @CachePut(cacheNames = "patients", key = "#patient.patientId")
    public void updatePatient(Patient patient) {
        System.out.println("updating in db and cache");
        patientRepository.save(patient);
    }

    public long parseId(String id) throws InvalidIDException {
        try {
            long patientId = Long.parseLong(id);
            return patientId;
        } catch (NumberFormatException e) {
            throw new InvalidIDException();
        }
    }

}
