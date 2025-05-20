package com.example.user_service.repository;

import com.example.user_service.model.Administrador;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface AdmRepository extends BaseRepository<Administrador> {
    Administrador findByLogin(String login);
}
