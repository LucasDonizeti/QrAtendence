package com.qratendence.qratendence;

import com.qratendence.qratendence.models.Admin;
import com.qratendence.qratendence.models.Event;
import com.qratendence.qratendence.models.Guest;
import com.qratendence.qratendence.models.Invitation;
import com.qratendence.qratendence.repository.AdminRepository;
import com.qratendence.qratendence.repository.EventRepository;
import com.qratendence.qratendence.repository.GuestRepository;
import com.qratendence.qratendence.repository.InvitationRepository;
import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Date;

/**
 * author LucasDonizeti
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class InvitationTest {
    @Autowired
    public InvitationRepository invitationDAO;
    @Autowired
    public GuestRepository guestDAO;
    @Autowired
    private AdminRepository adminDAO;
    @Autowired
    public EventRepository eventDAO;
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testInvitationSaveAndPut(){
        //criar admin
        Admin admin=new Admin("Lucas","321");
        admin.setId(0);
        Admin adminSave=adminDAO.save(admin);
        Assertions.assertThat(adminSave.getId()).isNotNull();
        //criar event
        Date data = new Date();
        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.HOUR_OF_DAY, 19);
        cal.set(Calendar.MINUTE, 30);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        data = cal.getTime();
        Event event = new Event("first test", "evento de teste 0.1", adminSave, data);
        event.setId(0);

        Event eventSave = eventDAO.save(event);

        Assertions.assertThat(eventSave.getId()).isNotNull();
        //criar guest
        Guest guest = new Guest("guest1","123");
        guest.setId(0);
        Guest guestSave = guestDAO.save(guest);

        Assertions.assertThat(guestSave.getId()).isNotNull();
        //criar invitation
        Invitation inv = new Invitation();
        inv.setPresence(false);
        inv.setEvent(eventSave);
        inv.setGuest(guestSave);
        inv.setId(0);
        Invitation invSave = invitationDAO.save(inv);

        Assertions.assertThat(invSave.getId()).isNotNull();
        Assertions.assertThat(invSave.getGuest()).isEqualTo(guestSave);
        Assertions.assertThat(invSave.getEvent()).isEqualTo(eventSave);
        Assertions.assertThat(invSave.getPresence()).isEqualTo(false);
        //modificar presenca
        Invitation invPresence = invitationDAO.findByEventAndGuest(eventSave,guestSave);
        Assertions.assertThat(invPresence.getId()).isNotNull();
        //verificar presenca modificada
        invPresence.setPresence(true);
        Assertions.assertThat(invPresence.getDatePresence()).isNotNull();
        Invitation finalINvitation = invitationDAO.save(invPresence);
        Assertions.assertThat(invPresence.getId()).isNotNull();
        Assertions.assertThat(invPresence.getGuest()).isEqualTo(guestSave);
        Assertions.assertThat(invPresence.getEvent()).isEqualTo(eventSave);
        Assertions.assertThat(invPresence.getPresence()).isEqualTo(true);
        Assertions.assertThat(invPresence.getDatePresence()).isNotNull();
    }
}
