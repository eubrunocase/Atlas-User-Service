package com.atlas.Atlas_User_Service.controller;

import com.atlas.Atlas_User_Service.dto.RegisterDTO;
import com.atlas.Atlas_User_Service.model.Professor;
import com.atlas.Atlas_User_Service.service.ProfessorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atlas/professores")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping
    public List<Professor> findAll() {
        return professorService.findAll();
    }

    @PostMapping
    public Professor create(@RequestBody RegisterDTO dto) {
        System.out.println("Recebido no controller: " + dto);
        Professor savedProfessor = professorService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProfessor).getBody();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        System.out.println("Deletando professor com o id: " + id);
        professorService.delete(id);
    }

}
