package com.example.patientmvcv2.repository;

import com.example.patientmvcv2.Entiter.patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends  JpaRepository <patient, Integer>{
    Page<patient> findByNomContains(String key , Pageable pageable);


}
