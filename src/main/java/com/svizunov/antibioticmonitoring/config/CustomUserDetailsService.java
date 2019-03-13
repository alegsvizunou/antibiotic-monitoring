package com.svizunov.antibioticmonitoring.config;

import com.svizunov.antibioticmonitoring.dao.DoctorRepository;
import com.svizunov.antibioticmonitoring.dao.RoleRepository;
import com.svizunov.antibioticmonitoring.model.Doctor;
import com.svizunov.antibioticmonitoring.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    RoleRepository roleRepository;

    final DoctorRepository doctorRepository;

    @Autowired
    public CustomUserDetailsService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }



    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
       Optional <Doctor> optionalDoctor = this.doctorRepository.findByLogin(login);
       Doctor doctor = new Doctor();
       if(optionalDoctor.isPresent()) {
           doctor = optionalDoctor.get();
       }

       List<Role> roles= roleRepository.findByDoctorId(doctor.getId());
       String rs = "";

       for(Role role : roles) {
           rs = rs + ", " + role.getName();
       }

       return new CustomUserDetails(doctor, rs);

    }

}

