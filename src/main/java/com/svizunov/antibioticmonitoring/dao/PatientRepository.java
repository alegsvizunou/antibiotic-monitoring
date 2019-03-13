package com.svizunov.antibioticmonitoring.dao;

import com.svizunov.antibioticmonitoring.model.Patient;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@PreAuthorize("hasRole('ROLE_DOCTOR')")
@Repository
public interface PatientRepository extends PagingAndSortingRepository<Patient, Long> {


    List<Patient> findBySurname(@Param("surname") String surname);
    List<Patient> findBySurnameAndFirstname(@Param("surname") String surname, @Param("firstname") String firstname);
    List<Patient> findBySurnameAndFirstnameAndLastname(@Param("surname") String surname, @Param("firstname") String firstname, @Param("lastname") String lastname);

    @Override
    Optional<Patient> findById(Long aLong);


    @Override
    @PreAuthorize("#s?.doctor?.id == authentication?.principal.id")
    <S extends Patient> S save(@Param("s") S s);

    @Override
    @PreAuthorize("@patientRepository.findOne(#id)?.doctor?.id == authentication?.principal.id")
    void deleteById(@Param("id") Long id);

    @Override
    @PreAuthorize("#patient?.doctor?.id == authentication?.principal.id")
    void delete(@Param("patient") Patient patient);
}

