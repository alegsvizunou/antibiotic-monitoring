package com.svizunov.antibioticmonitoring.dao;

import com.svizunov.antibioticmonitoring.model.DoctorClinic;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

@Repository
@PreAuthorize("hasRole('ROLE_DOCTOR')")
public interface DoctorClinicRepository extends PagingAndSortingRepository<DoctorClinic, Long> {
}
