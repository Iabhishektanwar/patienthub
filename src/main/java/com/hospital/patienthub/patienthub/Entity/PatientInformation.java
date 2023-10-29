package com.hospital.patienthub.patienthub.Entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class PatientInformation implements Serializable {
    private String firstName;
    private String secondName;
    private String dateOfBirth;
    private String gender;
    private String phoneNumber;
    private String email;
}
