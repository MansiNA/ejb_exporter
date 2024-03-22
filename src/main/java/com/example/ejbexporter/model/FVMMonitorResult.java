package com.example.ejbexporter.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name ="fvm_monitoring", schema="ekp")
public class FVMMonitorResult {
    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "RESULT")
    private String result;

    @Column(name = "ZEITPUNKT")
    private String zeitpunkt;

    @Column(name = "IS_ACTIVE")
    private String isActive;
}
