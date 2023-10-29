package com.hospital.patienthub.patienthub.Service;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {
    private final Environment env;

    public DatabaseService(Environment env) {
        this.env = env;
    }

    public String getDbUrl() {
        return env.getProperty("spring.datasource.url");
    }

    public String getDbDriverClassName() {
        return env.getProperty("spring.datasource.driver-class-name");
    }

    public String getDbUsername() {
        return env.getProperty("spring.datasource.username");
    }

    public String getDbPassword() {
        return env.getProperty("spring.datasource.password");
    }
}
