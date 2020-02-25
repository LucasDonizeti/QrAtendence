package com.qratendence.qratendence.service;

import com.qratendence.qratendence.models.Admin;
import com.qratendence.qratendence.repository.AdminRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * author LucasDonizeti
 */
@Component
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final AdminRepository adminDAO;

    public UserDetailsService(AdminRepository adminDAO) {
        this.adminDAO = adminDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin user = Optional.ofNullable(adminDAO.findByUsername(username)).orElseThrow(() -> new UsernameNotFoundException("user not found"));
        List<GrantedAuthority> authorityUser = AuthorityUtils.createAuthorityList("ROLE_USER");
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), (authorityUser));
    }
}
