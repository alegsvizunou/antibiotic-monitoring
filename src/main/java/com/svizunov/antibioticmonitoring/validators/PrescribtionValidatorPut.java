package com.svizunov.antibioticmonitoring.validators;

import com.svizunov.antibioticmonitoring.model.Prescribtion;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PrescribtionValidatorPut implements Validator {


    @Override
    public boolean supports(Class<?> aClass) {
        return Prescribtion.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        final Prescribtion prescribtion = (Prescribtion) o;

        if(!prescribtion.getEnddate().matches("(0?[1-9]|[12][0-9]|3[01])\\.(0?[1-9]|1[012])\\.((19|20)\\d\\d)")){
            errors.rejectValue("enddate", "Prescribtion.enddate.invalid", "Некорректная дата окончания терапии");
        }

        if(prescribtion.getEndcomment().equals("")){
            errors.rejectValue("endcomment", "Prescribtion.endcomment.invalid", "Некорректный комментарий окончания терапии");
        }

    }


}

