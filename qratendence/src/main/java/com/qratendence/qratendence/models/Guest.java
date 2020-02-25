package com.qratendence.qratendence.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * author LucasDonizeti
 */
@Entity
public class Guest extends User {
    @OneToMany
    private List<Invitation> invitationList;

    public Guest() {
    }

    public Guest(String username, String password) {
        super.username = username;
        super.password = password;
    }

    public Guest(String username, String password, List<Invitation> invitationList) {
        super.username = username;
        super.password = password;
        this.invitationList = invitationList;
    }

    public List<Invitation> getInvitationList() {
        return invitationList;
    }

    public void setInvitationList(List<Invitation> invitationList) {
        this.invitationList = invitationList;
    }
}
