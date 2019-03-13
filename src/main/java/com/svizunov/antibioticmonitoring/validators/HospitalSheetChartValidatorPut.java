package com.svizunov.antibioticmonitoring.validators;

import com.svizunov.antibioticmonitoring.model.HospitalSheetChart;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class HospitalSheetChartValidatorPut implements Validator {


    @Override
    public boolean supports(Class<?> aClass) {
        return HospitalSheetChart.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        final HospitalSheetChart hospitalSheetChart = (HospitalSheetChart) o;

        if(!hospitalSheetChart.getEnddate().matches("(0?[1-9]|[12][0-9]|3[01])\\.(0?[1-9]|1[012])\\.((19|20)\\d\\d)")){
            errors.rejectValue("startdate", "HospitalSheetChart.startdate.invalid", "Некорректная дата закрытия карточки");
        }

    }


}

