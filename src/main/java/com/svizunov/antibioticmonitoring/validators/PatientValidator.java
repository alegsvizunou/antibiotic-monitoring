package com.svizunov.antibioticmonitoring.validators;

import com.svizunov.antibioticmonitoring.model.Patient;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PatientValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Patient.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        final Patient patient = (Patient) o;

        if (!patient.getFirstname().matches("^[А-Я]{1}[а-я]{0,20}$")) {
            errors.rejectValue("firstname", "Patient.firstname.invalid", "Некорректное имя пациента");
        }

        if (!patient.getLastname().matches("^[А-Я]{1}[а-я]{0,20}$")) {
            errors.rejectValue("lastname", "Patient.lastname.invalid", "Некорректное отчество пациента");
        }

        if (!patient.getSurname().matches("^[А-Я]{1}[а-я]{0,20}$")) {
            errors.rejectValue("surname", "Patient.surname.invalid", "Некорректная фамилия пациента");
        }

        String birthyear = Integer.toString(patient.getBirthyear());

        if (!birthyear.matches("^[0-9]{4}$") || patient.getBirthyear() < 1900) {
            errors.rejectValue("birthyear", "Patient.birthyear.invalid", "Некорректный год рождения пациента");
        }

    }


}


