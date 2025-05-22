package com.atlas.Atlas_User_Service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "professor")
@EqualsAndHashCode(of = "id")
public class Professor extends Users{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private long id;


    public Professor(String login, String password, UserRoles role) {
        super(login, password, role);
    }

    public Professor() {
        super("", "", UserRoles.PROFESSOR);
    }

    @Override
    public String toString() {
        return "Professor{}";
    }

    @Override
    public String getUsername() {
        return this.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return super.isEnabled();
    }
}
