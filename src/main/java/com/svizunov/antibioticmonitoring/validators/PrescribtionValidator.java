package com.svizunov.antibioticmonitoring.validators;

import com.svizunov.antibioticmonitoring.model.Prescribtion;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PrescribtionValidator implements Validator {


    @Override
    public boolean supports(Class<?> aClass) {
        return Prescribtion.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        final Prescribtion prescribtion = (Prescribtion) o;

        if(!prescribtion.getStartdate().matches("(0?[1-9]|[12][0-9]|3[01])\\.(0?[1-9]|1[012])\\.((19|20)\\d\\d)")){
            errors.rejectValue("startdate", "Prescribtion.startdate.invalid", "Некорректная дата начала терапии");
        }

//        if(!String.valueOf(prescribtion.getDosage()).matches("[0-9]{1}[.]{1}[0-9]{2}")){
//            errors.rejectValue("dosage", "Prescribtion.dosage.invalid", "Некорректная доза препарата");
//        }

        if(!String.valueOf(prescribtion.getMultiplicity()).matches("[0-9]{1}")){
            errors.rejectValue("multiplicity", "Prescribtion.multiplicity.invalid", "Некорректная кратность назначения препарата");
        }

        if(prescribtion.getStartcomment().equals("")){
            errors.rejectValue("startcomment", "Prescribtion.startcomment.invalid", "Некорректный комментарий начала терапии");
        }

    }


}

