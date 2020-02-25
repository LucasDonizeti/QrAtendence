package com.qratendence.qratendence.models;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * author LucasDonizeti
 */
@Entity
public class Admin extends User {
    @OneToMany
    private List<Event> eventos;

    public Admin() {
    }

    public Admin(String username, String password) {
        super.password = new BCryptPasswordEncoder().encode(password);
        super.username = username;
    }

    public Admin(String username, String password, List<Event> eventos) {
        super.password = password;
        super.username = username;
        this.eventos = eventos;
    }

    @Override
    public void setPassword(String password) {
        super.password = new BCryptPasswordEncoder().encode(password);
    }

    public List<Event> getEventos() {
        return eventos;
    }

    public void setEventos(List<Event> eventos) {
        this.eventos = eventos;
    }
}
