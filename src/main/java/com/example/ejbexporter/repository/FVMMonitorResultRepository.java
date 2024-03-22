package com.example.ejbexporter.repository;

import com.example.ejbexporter.model.FVMMonitorResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FVMMonitorResultRepository extends JpaRepository<FVMMonitorResult, Integer> {

    // Add custom query methods if needed

}
