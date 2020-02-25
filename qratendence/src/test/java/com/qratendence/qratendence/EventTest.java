package com.qratendence.qratendence;

import com.qratendence.qratendence.models.Admin;
import com.qratendence.qratendence.models.Event;
import com.qratendence.qratendence.repository.AdminRepository;
import com.qratendence.qratendence.repository.EventRepository;
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
public class EventTest {

    @Autowired
    public EventRepository eventDAO;

    @Autowired
    public AdminRepository adminDAO;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void TestSaveEvent(){
        Admin admin=new Admin("Lucas","321");
        admin.setId(0);
        Admin adminSave=adminDAO.save(admin);

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
        Assertions.assertThat(eventSave.getAdmin()).isEqualTo(adminSave);
    }
}
