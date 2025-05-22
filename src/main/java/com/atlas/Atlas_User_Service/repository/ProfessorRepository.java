package com.atlas.Atlas_User_Service.repository;

import com.atlas.Atlas_User_Service.model.Professor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends BaseRepository<Professor> {
    Professor findByLogin(String login);
}
