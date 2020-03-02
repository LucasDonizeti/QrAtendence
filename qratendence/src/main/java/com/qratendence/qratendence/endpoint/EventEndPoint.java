package com.qratendence.qratendence.endpoint;

import com.qratendence.qratendence.models.Event;
import com.qratendence.qratendence.models.Guest;
import com.qratendence.qratendence.models.Invitation;
import com.qratendence.qratendence.repository.AdminRepository;
import com.qratendence.qratendence.repository.EventRepository;
import com.qratendence.qratendence.repository.GuestRepository;
import com.qratendence.qratendence.repository.InvitationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * author LucasDonizeti
 */
@RestController
@CrossOrigin
@RequestMapping("/v1/event")
public class EventEndPoint {
    private final EventRepository eventDAO;

    @Autowired
    public EventEndPoint(EventRepository eventDAO) {
        this.eventDAO = eventDAO;
    }

    @Autowired
    public AdminRepository adminDAO;
    @Autowired
    public InvitationRepository invitationDAO;
    @Autowired
    public GuestRepository guestDAO;

    //exemplo de evento json
    @GetMapping("/exemplo")
    public ResponseEntity<?> ex() {
        Long id = (long) 1;
        //Admin admin = adminDAO.findById(id).get();
        Date data = new Date();
        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.HOUR_OF_DAY, 19);
        cal.set(Calendar.MINUTE, 30);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        data = cal.getTime();
        Event event = new Event("first test", "evento de teste 0.1", adminDAO.findById(id).get(), data);
        event.setId(id);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    //todos os eventos
    @GetMapping("/all")
    public ResponseEntity<?> getAllEvent() {
        List<Event> eventos = eventDAO.findAll();
        return new ResponseEntity<>(eventos, HttpStatus.OK);
    }

    //evento por id
    @GetMapping("/{id}")
    public ResponseEntity<?> getEventById(@PathVariable("id") Long id) {
        if (eventDAO.existsById(id)) {
            return new ResponseEntity<>(eventDAO.findById(id).get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //invitations pelo id do evento
    @GetMapping("/{id}/invitation")
    public ResponseEntity<?> getInvitationByEventId(@PathVariable("id") Long id) {
        if (eventDAO.existsById(id)) {
            List<Invitation> invitationsList = invitationDAO.findByEventId(id);
            return new ResponseEntity<>(invitationsList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //dá presença a um guest pelo id do evento
    @PutMapping("/{eventId}/invitation/{guestId}/present")
    public ResponseEntity<?> putInvitation(@PathVariable long guestId, @PathVariable long eventId) {
        //if (eventDAO.existsById(id) && guestDAO.existsByUsername(guest.getUsername())) {
        if (invitationDAO.existsByEventIdAndGuestId(eventId, guestId)) {
            Event finderEvent = eventDAO.findById(eventId).get();
            Guest finderGuest = guestDAO.findById(guestId).get();
            Invitation inv = invitationDAO.findByEventAndGuest(finderEvent, finderGuest);
            inv.setPresence(true);
            return new ResponseEntity<>(invitationDAO.save(inv), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //adiciona um guest a lista de invitations pelo id do evento e id do guest
    @PostMapping("/{id}/invitation/{guestId}")
    public ResponseEntity<?> postInvitation(@PathVariable Long id, @PathVariable Long guestId) {
        if (eventDAO.existsById(id) && guestDAO.existsById(guestId)) {
            Guest guest = guestDAO.findById(guestId).get();
            Invitation inv = new Invitation(guestDAO.findByUsername(guest.getUsername()), eventDAO.findById(id).get(), false);
            return new ResponseEntity<>(invitationDAO.save(inv), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

}
