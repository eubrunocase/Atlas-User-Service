package com.example.user_service.controller;

import com.example.user_service.client.AuthClient;
import com.example.user_service.dto.CreateProfessorDTO;
import com.example.user_service.dto.ProfessorDTO;
import com.example.user_service.model.Professor;
import com.example.user_service.model.enums.UserRoles;
import com.example.user_service.service.ProfessorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user-service/professor")
@Tag(name = "Professores", description = "Endpoints para gerenciar professores")
public class ProfessorController {

    private final ProfessorService professorService;
    private final AuthClient authClient;

    public ProfessorController(ProfessorService professorService, AuthClient authClient) {
        this.professorService = professorService;
        this.authClient = authClient;
    }

    @PostMapping
    @Operation(summary = "Criar professor", description = "Cria um usuário do tipo professor")
    public ResponseEntity<ProfessorDTO> createProfessor(@RequestBody CreateProfessorDTO dto) {
        Professor professor = new Professor(dto.getLogin(), dto.getPassword(), UserRoles.PROFESSOR);
        Professor saved = professorService.save(professor);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDTO(saved));
    }

    @GetMapping
    @Operation(summary = "Listar professores", description = "Retorna uma lista com todos os professores")
    public List<ProfessorDTO> getAllProfessors() {
        return professorService.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Remover professor", description = "Remove um professor pelo ID")
    public void deleteProfessor(@PathVariable Long id) {
        professorService.delete(id);
    }

    @DeleteMapping
    @Operation(summary = "Remover todos os professores", description = "Exclui todos os professores cadastrados")
    public void deleteAll() {
        professorService.deleteAll();
    }

    @PutMapping("{id}")
    @Operation(summary = "Atualizar professor", description = "Atualiza as informações de um professor pelo ID")
    public ProfessorDTO updateProfessor(@PathVariable Long id, @RequestBody CreateProfessorDTO dto) {
        Professor professor = new Professor(dto.getLogin(), dto.getPassword(), UserRoles.PROFESSOR);
        professor.setId(id);
        Professor updated = professorService.save(professor);
        return toDTO(updated);
    }

    @GetMapping("/profile")
    @Operation(summary = "Recuperar informações", description = "Recupera as informações do professor baseado no token")
    public ResponseEntity<ProfessorDTO> findByToken(@RequestHeader("Authorization") String authHeader) {
        try {
            String token = authHeader.replace("Bearer ", "").trim();
            String login = authClient.validateTokenAndGetLogin(token);
            Professor profile = professorService.findyByLogin(login);
            return ResponseEntity.ok(toDTO(profile));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    private ProfessorDTO toDTO(Professor professor) {
        ProfessorDTO dto = new ProfessorDTO();
        dto.setId(professor.getId());
        dto.setLogin(professor.getLogin());
        dto.setRole(professor.getRole());
        return dto;
    }
}
