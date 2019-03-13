package com.svizunov.antibioticmonitoring.controllers;

import com.svizunov.antibioticmonitoring.dao.PatientRepository;
import com.svizunov.antibioticmonitoring.model.Doctor;
import com.svizunov.antibioticmonitoring.model.Patient;
import com.svizunov.antibioticmonitoring.model.Prescribtion;
import com.svizunov.antibioticmonitoring.validators.PatientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;


@RepositoryRestController
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientValidator patientValidator;

    @PostMapping(value = "/patients")

    public ResponseEntity savePatient(@RequestBody Patient p, BindingResult bindingResult) {


        Patient patient = new Patient();
        patient.setFirstname(p.getFirstname());
        patient.setLastname(p.getLastname());
        patient.setSurname(p.getSurname());
        patient.setBirthyear(p.getBirthyear());
        patient.setDoctor((Doctor) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        patientValidator.validate(patient, bindingResult);

        if(bindingResult.hasErrors()) {

            return new ResponseEntity (bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);

        } else {

            patientRepository.save(patient);
            return new ResponseEntity(HttpStatus.OK);
        }

    }


    @PutMapping(value = "/patients/{id}")
    public ResponseEntity putPatient(@PathVariable Long id, @RequestBody Patient p, BindingResult bindingResult) {

        Optional<Patient> patientOptional = patientRepository.findById(id);
        Patient patient = new Patient();
        if (patientOptional.isPresent()) {
            patient = patientOptional.get();
        }

        patient.setFirstname(p.getFirstname());
        patient.setLastname(p.getLastname());
        patient.setSurname(p.getSurname());
        patient.setBirthyear(p.getBirthyear());

        patientValidator.validate(patient, bindingResult);

        if(bindingResult.hasErrors()) {

            return new ResponseEntity (bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);

        } else {

            patientRepository.save(patient);
            return new ResponseEntity(HttpStatus.OK);
        }

    }
}

