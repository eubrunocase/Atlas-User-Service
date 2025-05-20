package com.example.user_service.controller;

import com.example.user_service.client.AuthClient;
import com.example.user_service.dto.AdministradorDTO;
import com.example.user_service.dto.CreateAdministradorDTO;
import com.example.user_service.model.Administrador;
import com.example.user_service.model.enums.UserRoles;
import com.example.user_service.service.AdmService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user-service/admin")
@Tag(name = "Administradores", description = "Endpoints para gerenciar administradores")
public class AdmController {

    private final AdmService admService;
    private final AuthClient authClient;

    public AdmController(AdmService admService, AuthClient authClient) {
        this.admService = admService;
        this.authClient = authClient;
    }

    @PostMapping
    @Operation(summary = "Criar administrador", description = "Cria um novo administrador")
    public ResponseEntity<AdministradorDTO> createAdm(@RequestBody CreateAdministradorDTO dto) {
        Administrador administrador = new Administrador(dto.getLogin(), dto.getPassword(), UserRoles.ADMINISTRADOR);
        Administrador saved = admService.save(administrador);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDTO(saved));
    }

    @GetMapping
    @Operation(summary = "Listar administradores", description = "Retorna todos os administradores cadastrados")
    public List<AdministradorDTO> getAllAdm() {
        return admService.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar por ID", description = "Remove um administrador pelo ID")
    public void deleteAdmById(@PathVariable Long id) {
        admService.delete(id);
    }

    @DeleteMapping
    @Operation(summary = "Deletar todos", description = "Remove todos os administradores")
    public void deleteAllAdm() {
        admService.deleteAll();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar administrador", description = "Atualiza os dados de um administrador pelo ID")
    public AdministradorDTO updateAdmById(@PathVariable Long id, @RequestBody CreateAdministradorDTO dto) {
        Administrador administrador = new Administrador(dto.getLogin(), dto.getPassword(), UserRoles.ADMINISTRADOR);
        administrador.setId(id);
        Administrador updated = admService.save(administrador);
        return toDTO(updated);
    }

    @GetMapping("/profile")
    @Operation(summary = "Recuperar informações", description = "Recupera as informações do administrador baseado no token")
    public ResponseEntity<AdministradorDTO> findByToken(@RequestHeader("Authorization") String authHeader) {
        try {
            String token = authHeader.replace("Bearer ", "").trim();
            String login = authClient.validateTokenAndGetLogin(token);
            Administrador profile = admService.findByLogin(login);
            return ResponseEntity.ok(toDTO(profile));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    private AdministradorDTO toDTO(Administrador administrador) {
        AdministradorDTO dto = new AdministradorDTO();
        dto.setId(administrador.getId());
        dto.setLogin(administrador.getLogin());
        dto.setRole(administrador.getRole());
        return dto;
    }
}
