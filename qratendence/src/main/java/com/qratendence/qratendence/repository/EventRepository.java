package com.qratendence.qratendence.repository;

import com.qratendence.qratendence.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * author LucasDonizeti
 */
public interface EventRepository extends JpaRepository<Event,Long> {
}
