package com.svizunov.antibioticmonitoring.dao;

import com.svizunov.antibioticmonitoring.model.Doctor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends PagingAndSortingRepository<Doctor, Long> {
    Optional<Doctor> findByLogin(String login);
}
