package com.qratendence.qratendence.repository;

import com.qratendence.qratendence.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * author LucasDonizeti
 */
public interface AdminRepository extends JpaRepository<Admin, Long> {
    public Admin findByUsername(String username);
}
