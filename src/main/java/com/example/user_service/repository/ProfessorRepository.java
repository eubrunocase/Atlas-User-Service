package com.example.user_service.repository;

import com.example.user_service.model.Professor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends BaseRepository<Professor> {
    Professor findByLogin(String login);
}
