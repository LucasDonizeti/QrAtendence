package com.qratendence.qratendence.endpoint;

import com.qratendence.qratendence.models.Admin;
import com.qratendence.qratendence.models.Event;
import com.qratendence.qratendence.models.Guest;
import com.qratendence.qratendence.repository.AdminRepository;
import com.qratendence.qratendence.repository.EventRepository;
import com.qratendence.qratendence.repository.GuestRepository;
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
@RequestMapping("/v1/admin")
public class AdminEndPoint {
    private final AdminRepository adminDAO;

    @Autowired
    public AdminEndPoint(AdminRepository adminDAO) {
        this.adminDAO = adminDAO;
    }

    @Autowired
    public EventRepository eventDAO;
    @Autowired
    private GuestRepository guestDAO;

    //todos os admins
    @GetMapping("/all")
    public ResponseEntity<?> getAllAdmin() {
        List<Admin> admins = adminDAO.findAll();
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }

    //exemplo de admin json
    @GetMapping("/exemplo")
    public ResponseEntity<?> ex() {
        Admin admin = new Admin("lucas", "123");
        admin.setId((long) 1);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    //retorna admin por id
    @GetMapping("/{id}")
    public ResponseEntity<?> getAdminById(@PathVariable("id") Long id) {
        if (adminDAO.existsById(id)) {
            return new ResponseEntity<>(adminDAO.findById(id).get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    //salva admin
    @PostMapping
    public ResponseEntity<?> postAdmin(@RequestBody Admin admin) {
        try {
            return new ResponseEntity<>(adminDAO.save(admin), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    //retorna eventos do admin por id
    @PostMapping("/{id}/event")
    public ResponseEntity<?> postEvent(@RequestBody Event event, @PathVariable Long id) {
        if (adminDAO.existsById(id)) {
            event.setAdmin(adminDAO.findById(id).get());
            return new ResponseEntity<>(eventDAO.save(event), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //adiciona guest ao sistema
    @PostMapping("/{id}/guest")
    public ResponseEntity<?> postGuest(@RequestBody Guest guest, @PathVariable Long id) {
        if (guestDAO.existsByUsername(guest.getUsername())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(guestDAO.save(guest), HttpStatus.CREATED);
        }
    }

    //atualiza admin
    @PutMapping("/{id}")
    public ResponseEntity<?> putAdmin(@RequestBody Admin admin, @PathVariable("id") Long id) {
        if (adminDAO.existsById(id)) {
            Admin newAdmin = adminDAO.findById(id).get();
            newAdmin.setPassword(admin.getPassword());
            newAdmin.setUsername(admin.getUsername());
            return new ResponseEntity<>(adminDAO.save(newAdmin), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //deleta admin
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable("id") Long id) {
        if (adminDAO.existsById(id)) {
            adminDAO.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
