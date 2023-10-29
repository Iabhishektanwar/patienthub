package com.hospital.patienthub.patienthub;

import com.hospital.patienthub.patienthub.Dao.PatientRepository;
import com.hospital.patienthub.patienthub.Entity.EmergencyContact;
import com.hospital.patienthub.patienthub.Entity.Patient;
import com.hospital.patienthub.patienthub.Entity.PatientInformation;
import com.hospital.patienthub.patienthub.Service.PatientService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class PatienthubApplicationTests {

	@Autowired
	private PatientService patientService;

	@MockBean
	private PatientRepository patientRepository;

	@Test
	public void savePatientTest() {
		Patient patient = new Patient();
		patient.setPatientId(99);
		when(patientRepository.save(patient)).thenReturn(patient);
		assertEquals(patient, patientService.insertPatient(patient));

	}

}
