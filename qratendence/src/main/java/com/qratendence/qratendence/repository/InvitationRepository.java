package com.qratendence.qratendence.repository;

import com.qratendence.qratendence.models.Event;
import com.qratendence.qratendence.models.Guest;
import com.qratendence.qratendence.models.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * author LucasDonizeti
 */
public interface InvitationRepository extends JpaRepository<Invitation, Long> {
    public Invitation findByEventAndGuest(Event event, Guest guest);

    public boolean existsByEventIdAndGuestId(long eventId, long guestId);

}
