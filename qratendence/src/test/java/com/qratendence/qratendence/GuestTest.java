package com.qratendence.qratendence;

import com.qratendence.qratendence.models.Guest;
import com.qratendence.qratendence.repository.GuestRepository;
import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * author LucasDonizeti
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class GuestTest {
    @Autowired
    public GuestRepository guestDAO;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void TestSaveGuest(){
        Guest guest = new Guest("guest1","123");
        guest.setId(0);
        Guest guestSave = guestDAO.save(guest);

        Assertions.assertThat(guestSave.getId()).isNotNull();
        Assertions.assertThat(guestSave.getUsername()).isEqualTo(guest.getUsername());
        Assertions.assertThat(guestSave.getPassword()).isEqualTo(guest.getPassword());
    }
}
