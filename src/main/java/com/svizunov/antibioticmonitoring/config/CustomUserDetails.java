package com.svizunov.antibioticmonitoring.config;

import com.svizunov.antibioticmonitoring.model.Doctor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


public class CustomUserDetails extends Doctor implements UserDetails {

    private final String roles;

    public CustomUserDetails(final Doctor doctor, String roles) {
        super(doctor);
        this.roles = roles;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
        return list;
    }



    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
