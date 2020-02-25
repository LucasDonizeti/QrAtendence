package com.qratendence.qratendence;

import com.qratendence.qratendence.models.Admin;
import com.qratendence.qratendence.repository.AdminRepository;
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
public class AdminTest {
    @Autowired
    private AdminRepository adminDAO;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void TestAdminSave(){
        Admin admin=new Admin("Lucas","321");
        admin.setId(0);
        Admin adminSaved=adminDAO.save(admin);
        Assertions.assertThat(adminSaved.getId()).isNotNull();
        Assertions.assertThat(admin.getUsername()).isEqualTo(adminSaved.getUsername());
        Assertions.assertThat(admin.getPassword()).isEqualTo(adminSaved.getPassword());
    }
}
