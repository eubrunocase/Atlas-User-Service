package com.atlas.Atlas_User_Service.repository;

import com.atlas.Atlas_User_Service.model.Administrador;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends BaseRepository<Administrador> {
    Administrador findByLogin(String login);
}
