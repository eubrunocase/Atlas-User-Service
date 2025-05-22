package com.atlas.Atlas_User_Service.service;

import com.atlas.Atlas_User_Service.dto.RegisterDTO;
import com.atlas.Atlas_User_Service.model.Professor;
import com.atlas.Atlas_User_Service.repository.ProfessorRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final PasswordEncoder encoder = new BCryptPasswordEncoder();

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public Professor save (RegisterDTO dto) {
        Professor p = new Professor(dto.login(), encoder.encode(dto.password()), dto.role());
        return professorRepository.save(p);
    }

     public Professor findByLogin(String login) {
        return professorRepository.findByLogin(login);
     }

    public void delete(Long id) {
        professorRepository.deleteById(id);
    }

    public List<Professor> findAll() {
        return professorRepository.findAll();
    }





}
