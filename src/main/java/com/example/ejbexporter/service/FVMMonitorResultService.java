package com.example.ejbexporter.service;

import com.example.ejbexporter.model.FVMMonitorResult;
import com.example.ejbexporter.repository.FVMMonitorResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FVMMonitorResultService {

    private final FVMMonitorResultRepository repository;

    @Autowired
    public FVMMonitorResultService(FVMMonitorResultRepository repository) {
        this.repository = repository;
    }

    public List<FVMMonitorResult> getAllFVMMonitorResults() {
        return repository.findAll();
    }

    // Add more methods as needed

}
