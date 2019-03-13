package com.svizunov.antibioticmonitoring.controllers;

import com.svizunov.antibioticmonitoring.dao.ClinicRepository;
import com.svizunov.antibioticmonitoring.dao.HospitalSheetChartRepository;
import com.svizunov.antibioticmonitoring.dao.PatientRepository;
import com.svizunov.antibioticmonitoring.dao.PrescribtionRepository;
import com.svizunov.antibioticmonitoring.model.Clinic;
import com.svizunov.antibioticmonitoring.model.Doctor;
import com.svizunov.antibioticmonitoring.model.HospitalSheetChart;
import com.svizunov.antibioticmonitoring.model.Patient;
import com.svizunov.antibioticmonitoring.validators.HospitalSheetChartValidator;
import com.svizunov.antibioticmonitoring.validators.HospitalSheetChartValidatorPut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RepositoryRestController
public class HospitalSheetChartController {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private HospitalSheetChartRepository hospitalSheetChartRepository;

    @Autowired
    private ClinicRepository clinicRepository;

    @Autowired
    private PrescribtionRepository prescribtionRepository;

    @Autowired
    private HospitalSheetChartValidator hospitalSheetChartValidator;

    @Autowired
    private HospitalSheetChartValidatorPut hospitalSheetChartValidatorPut;

    @PostMapping(value = "/patients/{id}/hospitalSheetCharts")
    public ResponseEntity saveHospitalSheetChart(@PathVariable Long id, @RequestBody HospitalSheetChart h, BindingResult bindingResult) {
        Optional<Patient> patientOptional = patientRepository.findById(id);
        Patient patient = new Patient();
        if (patientOptional.isPresent()) {
            patient = patientOptional.get();
        }

        Optional<Clinic> clinicOptional = clinicRepository.findById(h.getClinic().getId());

        Clinic clinic = new Clinic();
        if (clinicOptional.isPresent()) {
            clinic = clinicOptional.get();
        }

        String stdate = h.getStartdate();

        if (stdate.matches("[0-9]{1}[.]{1}[0-9]{1}[.]{1}[0-9]{4}") || stdate.matches("[0-9]{2}[.]{1}[0-9]{1}[.]{1}[0-9]{4}") || stdate.matches("[0-9]{2}[.]{1}[0-9]{2}[.]{1}[0-9]{4}") || stdate.matches("[0-9]{1}[.]{1}[0-9]{2}[.]{1}[0-9]{4}")) {

            if (stdate.length() < 10) {
                if (String.valueOf(stdate.charAt(1)).equals(".")) {
                    stdate = "0" + stdate;
                }
                if (String.valueOf(stdate.charAt(4)).equals(".")) {
                    stdate = stdate.subSequence(0, 3) + "0" + stdate.subSequence(3, 9);
                }

            }

        } else {
            stdate = "0";
        }

        HospitalSheetChart hospitalSheetChart = new HospitalSheetChart();
        hospitalSheetChart.setStartdate(stdate);
        hospitalSheetChart.setNumber(h.getNumber());
        hospitalSheetChart.setB(h.isB());
        hospitalSheetChart.setClinic(clinic);
        hospitalSheetChart.setPatient(patient);
        hospitalSheetChart.setDoctor((Doctor) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        hospitalSheetChartValidator.validate(hospitalSheetChart, bindingResult);

        if (bindingResult.hasErrors()) {

            return new ResponseEntity(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);

        } else {

            hospitalSheetChartRepository.save(hospitalSheetChart);
            return new ResponseEntity(HttpStatus.OK);
        }
    }

    @PutMapping(value = "/hospitalSheetCharts/{id}")
    public ResponseEntity putHospitalSheetChart(@PathVariable Long id, @RequestBody HospitalSheetChart h, BindingResult bindingResult) {
        Optional<HospitalSheetChart> optionalHospitalSheetChart = hospitalSheetChartRepository.findById(id);
        HospitalSheetChart hospitalSheetChart = new HospitalSheetChart();
        if (optionalHospitalSheetChart.isPresent()) {
            hospitalSheetChart = optionalHospitalSheetChart.get();
        }

        Doctor d = (Doctor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (clinicRepository.findClinicsByDoctorId(d.getId()).contains(hospitalSheetChart.getClinic())) {

            String enddate = h.getEnddate();

            if (enddate.matches("[0-9]{1}[.]{1}[0-9]{1}[.]{1}[0-9]{4}") || enddate.matches("[0-9]{2}[.]{1}[0-9]{1}[.]{1}[0-9]{4}") || enddate.matches("[0-9]{2}[.]{1}[0-9]{2}[.]{1}[0-9]{4}") || enddate.matches("[0-9]{1}[.]{1}[0-9]{2}[.]{1}[0-9]{4}")) {

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
            hospitalSheetChart.setEnddate(enddate);
            hospitalSheetChart.setB(h.isB());

            hospitalSheetChartValidatorPut.validate(hospitalSheetChart, bindingResult);

            if (bindingResult.hasErrors()) {

                return new ResponseEntity(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);

            } else {

                hospitalSheetChartRepository.save(hospitalSheetChart);
                return new ResponseEntity(HttpStatus.OK);
            }


        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }
}


