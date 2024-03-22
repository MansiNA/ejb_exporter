package com.example.ejbexporter.service;

import com.example.ejbexporter.model.FVMMonitoring;
import com.example.ejbexporter.repository.FVMMonitoringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FVMMonitoringService {

    private final FVMMonitoringRepository repository;

    @Autowired
    public FVMMonitoringService(FVMMonitoringRepository repository) {
        this.repository = repository;
    }

    public List<FVMMonitoring> getAllFVMMonitorings() {
        return repository.findAll();
    }

    // Add more methods as needed

}
