package com.svizunov.antibioticmonitoring.controllers;

import com.svizunov.antibioticmonitoring.dao.AntibioticRepository;
import com.svizunov.antibioticmonitoring.dao.ClinicRepository;
import com.svizunov.antibioticmonitoring.dao.HospitalSheetChartRepository;
import com.svizunov.antibioticmonitoring.dao.PrescribtionRepository;
import com.svizunov.antibioticmonitoring.model.Antibiotic;
import com.svizunov.antibioticmonitoring.model.Doctor;
import com.svizunov.antibioticmonitoring.model.HospitalSheetChart;
import com.svizunov.antibioticmonitoring.model.Prescribtion;
import com.svizunov.antibioticmonitoring.validators.PrescribtionValidator;
import com.svizunov.antibioticmonitoring.validators.PrescribtionValidatorPut;
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
public class PrescribtionController {


    @Autowired
    private HospitalSheetChartRepository hospitalSheetChartRepository;

    @Autowired
    private PrescribtionRepository prescribtionRepository;

    @Autowired
    private AntibioticRepository antibioticRepository;

    @Autowired
    private ClinicRepository clinicRepository;

    @Autowired
    private PrescribtionValidator prescribtionValidator;

    @Autowired
    private PrescribtionValidatorPut prescribtionValidatorPut;

    @PostMapping(value = "/hospitalSheetCharts/{id}/prescribtions")
    public ResponseEntity<?> savePrescribtion(@PathVariable Long id, @RequestBody Prescribtion p, BindingResult bindingResult) {

        Optional<HospitalSheetChart> hospitalSheetChartOptional = hospitalSheetChartRepository.findById(id);
        HospitalSheetChart hospitalSheetChart = new HospitalSheetChart();
        if (hospitalSheetChartOptional.isPresent()) {
            hospitalSheetChart = hospitalSheetChartOptional.get();
        }

        Doctor d = (Doctor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (clinicRepository.findClinicsByDoctorId(d.getId()).contains(hospitalSheetChart.getClinic()) ) {

            String stdate = p.getStartdate();

            if (stdate.matches("[0-9]{1}[.]{1}[0-9]{1}[.]{1}[0-9]{4}") ||
                    stdate.matches("[0-9]{2}[.]{1}[0-9]{1}[.]{1}[0-9]{4}") ||
                    stdate.matches("[0-9]{2}[.]{1}[0-9]{2}[.]{1}[0-9]{4}") ||
                    stdate.matches("[0-9]{1}[.]{1}[0-9]{2}[.]{1}[0-9]{4}") ) {

            if (stdate.length() < 10) {
                if (String.valueOf(stdate.charAt(1)).equals(".")) {
                    stdate = "0" + stdate;
                }
                if (String.valueOf(stdate.charAt(4)).equals(".")) {
                    stdate = stdate.subSequence(0, 3) + "0" + stdate.subSequence(3, 9);
                }

            }
            }  else {
                stdate = "0";
            }

            Optional<Antibiotic> antibioticOptional = antibioticRepository.findById(p.getAntibiotic().getId());

            Antibiotic antibiotic = new Antibiotic();
            if (antibioticOptional.isPresent()) {
                antibiotic = antibioticOptional.get();
            }

            Prescribtion prescribtion = new Prescribtion();
            prescribtion.setStartdate(stdate);
            prescribtion.setStartcomment(p.getStartcomment());
            prescribtion.setAntibiotic(antibiotic);
            prescribtion.setMultiplicity(p.getMultiplicity());
            prescribtion.setDosage(p.getDosage());
            prescribtion.setHospitalSheetChart(hospitalSheetChart);
            prescribtion.setB(p.isB());
            prescribtion.setDoctor((Doctor) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

            prescribtionValidator.validate(prescribtion, bindingResult);

            if(bindingResult.hasErrors()) {
                return new ResponseEntity (bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);

            } else {
                prescribtionRepository.save(prescribtion);
                return new ResponseEntity<>(HttpStatus.OK);
            }


        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping(value = "/prescribtions/{id}")
    public ResponseEntity<?> putPrescribtion(@PathVariable Long id, @RequestBody Prescribtion p, BindingResult bindingResult) {

        Optional<Prescribtion> prescribtionOptional = prescribtionRepository.findById(id);
        Prescribtion prescribtion = new Prescribtion();
        if (prescribtionOptional.isPresent()) {
            prescribtion = prescribtionOptional.get();
        }

        Doctor d = (Doctor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if ( clinicRepository.findClinicsByDoctorId(d.getId()).contains(prescribtion.getHospitalSheetChart().getClinic()) ) {

            String enddate = p.getEnddate();

            if (enddate.matches("[0-9]{1}[.]{1}[0-9]{1}[.]{1}[0-9]{4}") ||
                    enddate.matches("[0-9]{2}[.]{1}[0-9]{1}[.]{1}[0-9]{4}") ||
                    enddate.matches("[0-9]{2}[.]{1}[0-9]{2}[.]{1}[0-9]{4}") ||
                    enddate.matches("[0-9]{1}[.]{1}[0-9]{2}[.]{1}[0-9]{4}") ) {

            if (enddate.length() < 10) {
                if (String.valueOf(enddate.charAt(1)).equals(".")) {
                    enddate = "0" + enddate;
                }
                if (String.valueOf(enddate.charAt(4)).equals(".")) {
                    enddate = enddate.subSequence(0, 3) + "0" + enddate.subSequence(3, 9);
                }

            }
            } else {
                enddate = "0";
            }
            prescribtion.setEnddate(enddate);
            prescribtion.setEndcomment(p.getEndcomment());
            prescribtion.setB(p.isB());
            prescribtion.setDoctor2((Doctor) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

            prescribtionValidatorPut.validate(prescribtion, bindingResult);

            if(bindingResult.hasErrors()) {
                return new ResponseEntity (bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);

            } else {
                prescribtionRepository.save(prescribtion);
                return new ResponseEntity<>(HttpStatus.OK);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }





}




