package com.hospital.patienthub.patienthub.Entity;

import lombok.Data;

@Data
public class DatabaseSource {
    private String url;
    private String driverClassName;
    private String username;
    private String password;
}
