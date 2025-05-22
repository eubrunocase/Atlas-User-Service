package com.atlas.Atlas_User_Service.controller;

import com.atlas.Atlas_User_Service.dto.RegisterDTO;
import com.atlas.Atlas_User_Service.dto.UserDetailsDTO;
import com.atlas.Atlas_User_Service.model.Users;
import com.atlas.Atlas_User_Service.service.AdminService;
import com.atlas.Atlas_User_Service.service.ProfessorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/internal/users")
public class InternalUserController {

    private final ProfessorService professorService;
    private final AdminService adminService;

    public InternalUserController(ProfessorService professorService, AdminService adminService) {
        this.professorService = professorService;
        this.adminService = adminService;
    }

    @PostMapping
    public ResponseEntity<Void> register(@RequestBody RegisterDTO dto) {
        switch (dto.role()) {
            case PROFESSOR -> professorService.save(dto);
            case ADMINISTRADOR -> adminService.save(dto);
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping("/{login}")
    public ResponseEntity<UserDetailsDTO> findByLogin (@PathVariable String login) {
        Users user = adminService.findByLogin(login);
        if (user == null) user = professorService.findByLogin(login);
        if (user == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(new UserDetailsDTO(user.getLogin(), user.getPassword(),user.getEscola(), user.getRole()));
    }

}
