package com.qratendence.qratendence.endpoint;

import com.qratendence.qratendence.models.Guest;
import com.qratendence.qratendence.models.Invitation;
import com.qratendence.qratendence.repository.EventRepository;
import com.qratendence.qratendence.repository.GuestRepository;
import com.qratendence.qratendence.repository.InvitationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * author LucasDonizeti
 */
@RestController
@CrossOrigin
@RequestMapping("/v1/guest")
public class GuestEndPoint {
    private final GuestRepository guestDAO;

    @Autowired
    public GuestEndPoint(GuestRepository guestDAO) {
        this.guestDAO = guestDAO;
    }

    @Autowired
    public InvitationRepository invitationDAO;

    //exemplo guest json
    @GetMapping("/exemplo")
    public ResponseEntity<?> ex() {
        Guest guest = new Guest("guest1", "123");
        guest.setId((long) 1);
        return new ResponseEntity<>(guest, HttpStatus.OK);
    }

    //guest por id
    @GetMapping("/{id}")
    public ResponseEntity<?> getGuest(@PathVariable Long id) {
        if (guestDAO.existsById(id)) {
            return new ResponseEntity<>(guestDAO.findById(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //todos os guests
    @GetMapping("/all")
    public ResponseEntity<?> getAllGuests() {
        return new ResponseEntity<>(guestDAO.findAll(), HttpStatus.OK);
    }

    //todos os eventos pelo guest id
    @GetMapping("/{id}/events")
    public ResponseEntity<?> getEventsByGuestId(@PathVariable long id) {
        if (guestDAO.existsById(id)) {
            Guest guest = guestDAO.findById(id).get();
            List<Invitation> invList = guest.getInvitationList();
            return new ResponseEntity<>(invList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
