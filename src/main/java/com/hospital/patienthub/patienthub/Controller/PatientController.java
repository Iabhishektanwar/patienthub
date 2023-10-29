package com.hospital.patienthub.patienthub.Controller;

import com.hospital.patienthub.patienthub.Entity.DatabaseSource;
import com.hospital.patienthub.patienthub.Entity.Patient;
import com.hospital.patienthub.patienthub.Exceptions.InvalidIDException;
import com.hospital.patienthub.patienthub.Exceptions.ResourceNotFoundException;
import com.hospital.patienthub.patienthub.Service.DatabaseService;
import com.hospital.patienthub.patienthub.Service.PatientServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private final PatientServiceImpl patientServiceImpl;

    @Autowired
    private final DatabaseService databaseService;


    @GetMapping("/database_properties")
    public DatabaseSource getDataBaseProperties() {

        // Externalize application properties

        DatabaseSource ds = new DatabaseSource();
        ds.setUrl(databaseService.getDbUrl());
        ds.setDriverClassName(databaseService.getDbDriverClassName());
        ds.setUsername(databaseService.getDbUsername());
        ds.setPassword(databaseService.getDbPassword());

        return ds;
    }

    @GetMapping("/")
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> allPatients = patientServiceImpl.getAllPatients();

        if (allPatients.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(allPatients);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPatient(@PathVariable("id") String id) {
        try {
            long patientId = patientServiceImpl.parseId(id);
            Patient patient = patientServiceImpl.getPatientById(patientId);
            return ResponseEntity.ok(patient);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Patient available with the given ID");
        } catch (InvalidIDException e) {
            // Handle the case where the user enters a non-numeric value
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid ID format. Please enter a valid numeric ID.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> addPatient(@RequestBody Patient patient) {
        try {
            if (patient == null) {
                return ResponseEntity.badRequest().body("Invalid user data"); // Use 400 Bad Request for invalid data.
            }

            // Insert the new patient and get the created patient object.
            Patient createdUser = patientServiceImpl.insertPatient(patient);

            if (createdUser != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(createdUser); // Use 201 Created for successful creation.
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User creation failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePatient(@RequestBody Patient patient, @PathVariable("id") String id) {
        try {
            long patientId = patientServiceImpl.parseId(id);
            Patient existingPatient = patientServiceImpl.getPatientById(patientId);

            // Validate the user object or individual fields, if needed.
            if (patient.getPatientName() != null) {
                existingPatient.setPatientName(patient.getPatientName());
            }
            if (patient.getPatientInformation() != null) {
                existingPatient.setPatientInformation(patient.getPatientInformation());
            }
            if (patient.getEmergencyContact() != null) {
                existingPatient.setEmergencyContact(patient.getEmergencyContact());
            }

            Patient updatedPatient = patientServiceImpl.insertPatient(existingPatient);

            return ResponseEntity.ok(updatedPatient); // Use 200 OK for a successful update.
        } catch (ResourceNotFoundException e) {
            // Handle the case where the patient is not found.
            return ResponseEntity.notFound().build(); // Use 404 Not Found for missing patient.
        } catch (InvalidIDException e) {
            // Handle the case where the patient id is invalid.
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable("id") String id) throws InvalidIDException {
        try {
            long patientId = patientServiceImpl.parseId(id);
            patientServiceImpl.deletePatient(patientId);
            return ResponseEntity.noContent().build(); // Use 204 No Content for successful deletion.
        } catch (ResourceNotFoundException e) {
            // Handle the case where the patient is not found.
            return ResponseEntity.notFound().build(); // Use 404 Not Found for missing patient.
        } catch (InvalidIDException e) {
            // Handle the case where the patient id is invalid.
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            // Handle other exceptions (e.g., service failure).
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
