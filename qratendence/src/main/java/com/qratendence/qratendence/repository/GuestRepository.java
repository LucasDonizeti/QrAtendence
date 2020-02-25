package com.qratendence.qratendence.repository;

import com.qratendence.qratendence.models.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * author LucasDonizeti
 */
public interface GuestRepository extends JpaRepository<Guest,Long> {
    public Boolean existsByUsername(String username);
    public Guest findByUsername(String username);

}
