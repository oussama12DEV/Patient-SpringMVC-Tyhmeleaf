package com.example.patientmvcv2;

import com.example.patientmvcv2.Entiter.patient;
import com.example.patientmvcv2.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import java.util.Date;

@SpringBootApplication

public class PatientMvcv2Application {

    public static void main(String[] args)
    {
        SpringApplication.run(PatientMvcv2Application.class, args);
    }
    @Autowired
    PatientRepository patientRepository;
    @Bean
    CommandLineRunner commandLineRunner(){
        return args -> {
            patientRepository.save(new patient(0,"oussama",new Date(),false,99));
            patientRepository.save(new patient(0,"anass",new Date(),true,50));
            patientRepository.save(new patient(0,"hassan",new Date(),false,20));
            patientRepository.findAll().forEach(patient -> {
                System.out.println(patient.getId());
                System.out.println(patient.getNom());
            });
        };
    }

}
