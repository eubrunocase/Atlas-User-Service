package com.atlas.Atlas_User_Service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
public abstract class Users implements UserDetails {

    @JsonProperty("login")
    private String login;

    @JsonProperty("password")
    private String password;

    @JsonProperty("escola")
    private String escola;

    @Enumerated(EnumType.STRING)
    @JsonProperty("role")
    private UserRoles role;

    public Users(String login, String password, UserRoles role) {
        this.login = login;
        this.password = password;
        this.escola = escola;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.role.name()));
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    @Override
    public String toString() {
        return "Users{" +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }








}
