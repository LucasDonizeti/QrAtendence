package com.qratendence.qratendence.repository;

import com.qratendence.qratendence.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * author LucasDonizeti
 */
public interface EventRepository extends JpaRepository<Event,Long> {
    public List<Event> findEventByAdminId(long id);
}
