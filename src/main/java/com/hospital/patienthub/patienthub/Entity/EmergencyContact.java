package com.hospital.patienthub.patienthub.Entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmergencyContact implements Serializable {
    private int houseNumber;
    private String street;
    private String city;
    private String state;
    private String country;
    private int pincode;
    private String contact;
}
