package com.svizunov.antibioticmonitoring.dao;

import com.svizunov.antibioticmonitoring.model.Prescribtion;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.util.List;

@PreAuthorize("hasRole('ROLE_DOCTOR')")
@Repository
public interface PrescribtionRepository extends PagingAndSortingRepository<Prescribtion, Long> {
    List<Prescribtion> findByHospitalSheetChartId(Long id);

    @Override
    @PreAuthorize("@prescribtionRepository.findOne(#id)?.doctor?.id == authentication?.principal.id")
    void deleteById(@Param("id") Long id);

    @Override
    @PreAuthorize("#prescribtion?.doctor?.id == authentication?.principal.id")
    void delete(@Param("prescribtion") Prescribtion prescribtion);

}
