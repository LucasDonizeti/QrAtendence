package com.qratendence.qratendence.models;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.List;

/**
 * author LucasDonizeti
 */
@Entity
public class Event extends AbstractEntity {
    @NotNull
    private String nome;
    @NotNull
    private String descricao;
    @OneToOne
    @NotNull
    private Admin admin;
    @OneToMany
    private List<Invitation> invitationList;

    @Temporal(TemporalType.DATE)
    private Date dateInit;

    public Event() {
    }

    public Event(String nome, String descricao, Admin admin, Date dateInit) {
        this.nome = nome;
        this.descricao = descricao;
        this.admin = admin;
        this.dateInit = dateInit;
    }

    public String getLinkQrcode() {
        return "http://api.qrserver.com/v1/create-qr-code/?data=http://localhost:8080/v1/event/" + this.getId();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public List<Invitation> getInvitationList() {
        return invitationList;
    }

    public void setInvitationList(List<Invitation> invitationList) {
        this.invitationList = invitationList;
    }

    public Date getDateInit() {
        return dateInit;
    }

    public void setDateInit(Date dateInit) {
        this.dateInit = dateInit;
    }
}
