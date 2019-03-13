package com.svizunov.antibioticmonitoring.dao;

import com.svizunov.antibioticmonitoring.model.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends PagingAndSortingRepository<Role, Integer> {


@Query("select r from Role r join fetch r.doctorRoles dr join fetch dr.doctor where dr.doctor.id=?1")
List<Role> findByDoctorId(Long id);

}
