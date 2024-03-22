package com.example.ejbexporter.repository;

import com.example.ejbexporter.model.FVMMonitoring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FVMMonitoringRepository extends JpaRepository<FVMMonitoring, Integer> {

    // Add custom query methods if needed

}
