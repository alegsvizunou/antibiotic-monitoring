package com.svizunov.antibioticmonitoring.dao;


import com.svizunov.antibioticmonitoring.model.Clinic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@PreAuthorize("hasRole('ROLE_DOCTOR')")
public interface ClinicRepository extends PagingAndSortingRepository<Clinic, Long> {

    @Query("select c from Clinic c join fetch c.doctorClinics dc join fetch dc.doctor where dc.doctor.id=?1")
    List<Clinic> findClinicsByDoctorId(Long id);

    @Query("select c from Clinic c join fetch c.doctorClinics dc join fetch dc.doctor where dc.doctor.id = ?#{principal.id}")
    List<Clinic> findClinicsByDoctorAuthenticated();



}

