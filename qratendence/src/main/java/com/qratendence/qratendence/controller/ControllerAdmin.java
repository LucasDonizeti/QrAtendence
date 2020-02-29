package com.qratendence.qratendence.controller;

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
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * author LucasDonizeti
 */
@RestController
@CrossOrigin
@RequestMapping("/v2/admin")
public class ControllerAdmin {

        private final AdminRepository adminDAO;

        @Autowired
        public ControllerAdmin(AdminRepository adminDAO) {
            this.adminDAO = adminDAO;
        }

        @Autowired
        public EventRepository eventDAO;
        @Autowired
        private GuestRepository guestDAO;

        //todos os admins
        @GetMapping("/all")
        public ModelAndView getAllAdmin() {
            ModelAndView md = new ModelAndView("/teste");
            md.addObject("admins",adminDAO.findAll());
            return md;
        }

        //eventos do admin por id

    @GetMapping("/{id}/event")
    public ModelAndView getEventByAdminId(@PathVariable long id){
            //if(adminDAO.existsById(id)){
                Admin admin = adminDAO.findById(id).get();
                List<Event> eventList = eventDAO.findEventByAdminId(id);

                ModelAndView mav=new ModelAndView("/testeEventos");
                mav.addObject("admin",admin);
                mav.addObject("eventos",eventList);
                mav.addObject("qr",eventList.get(0).getLinkQrcode());
                return mav;
            //}else {
            //    ModelAndView me=new ModelAndView("/erro");
            //    return me;
            //}
    }


    }
