package com.qratendence.qratendence.models;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * author LucasDonizeti
 */
@Entity
public class Invitation extends AbstractEntity {

    @OneToOne
    @NotNull
    private Guest guest;
    @OneToOne
    @NotNull
    private Event event;

    @Temporal(TemporalType.DATE)
    private Date datePresence;

    private Boolean presence;

    public Invitation() {
    }

    public Invitation(Guest guest, Event event, Boolean presence) {
        this.guest = guest;
        this.event = event;
        this.presence = presence;
    }

    public Invitation(Guest guest, Event event, Date datePresence, Boolean presence) {
        this.guest = guest;
        this.event = event;
        this.datePresence = datePresence;
        this.presence = presence;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Date getDatePresence() {
        return datePresence;
    }

    public void setDatePresence(Date datePresence) {
        this.datePresence = datePresence;
    }

    public Boolean getPresence() {
        return presence;
    }

    public void setPresence(Boolean presence) {
        if (presence == true) {
            Date data = new Date();
            this.datePresence = data;
        }
        this.presence = presence;
    }
}
