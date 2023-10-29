package com.hospital.patienthub.patienthub.Entity;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import jakarta.persistence.*;
import lombok.Data;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;

import java.util.UUID;

@Entity
@Table(name = "patients")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Patient implements Serializable {

    @Id
    @Column(name = "patient_id", unique = true, nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long patientId;

    @Column(name = "patient_name")
    private String patientName;

    @Column(name = "patient_information", columnDefinition="json")
    @JdbcTypeCode(SqlTypes.JSON)
    @Type(JsonStringType.class)
    private PatientInformation patientInformation;

    @Column(name = "emergency_contact", columnDefinition="json")
    @JdbcTypeCode(SqlTypes.JSON)
    @Type(JsonStringType.class)
    private EmergencyContact emergencyContact;

    private String createdBy;

    @CreationTimestamp
    private LocalDateTime createdDate;

    private String updatedBy;

    @UpdateTimestamp
    private LocalDateTime updatedDate;

    @PrePersist
    protected void onCreate() throws UnknownHostException {
        this.createdBy = InetAddress.getLocalHost().getHostName();
    }

    @PreUpdate
    protected void onUpdate() throws UnknownHostException {
        this.updatedBy = InetAddress.getLocalHost().getHostName();
    }
}