package com.svizunov.antibioticmonitoring.dao;

import com.svizunov.antibioticmonitoring.model.HospitalSheetChart;
import com.svizunov.antibioticmonitoring.model.Prescribtion;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@PreAuthorize("hasRole('ROLE_DOCTOR')")
@Repository
public interface HospitalSheetChartRepository extends PagingAndSortingRepository<HospitalSheetChart, Long> {


    @Override
    @PreAuthorize("@hospitalSheetChartRepository.findOne(#id)?.doctor?.id == authentication?.principal.id")
    void deleteById(@Param("id") Long id);

    @Override
    @PreAuthorize("#hospitalSheetChart?.doctor?.id == authentication?.principal.id")
    void delete(@Param("hospitalSheetChart") HospitalSheetChart hospitalSheetChart);

    Optional<HospitalSheetChart> findByPrescribtions(Prescribtion prescribtion);
}
