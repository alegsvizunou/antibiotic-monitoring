package com.svizunov.antibioticmonitoring.dao;

import com.svizunov.antibioticmonitoring.model.DoctorRole;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRoleRepository extends PagingAndSortingRepository<DoctorRole, Integer> {
}
