package com.qratendence.qratendence.models;

import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * author LucasDonizeti
 */
@MappedSuperclass
public class User extends AbstractEntity{
    @NotNull
    protected String username;
    @NotNull
    protected String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
