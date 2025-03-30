package com.example.Consumer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalseInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime createate; // Store the date and time as LocalDateTime
    String site;            // Site as a string
    Double mainsVoltage;     // Mains voltage as a Double
    Double batteryVoltage;   // Battery voltage as a Double
    Double enclosureTemp;   // Enclosure temperature as a Double
    String st;               // ST (status) field as String
    String statusData;       // STATUS DATA as String
    String statusBits;       // STATUS BITS as String
    LocalDateTime timeData;   // TIME DATA as LocalDateTime

}
