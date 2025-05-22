package com.atlas.Atlas_User_Service.controller;

import com.atlas.Atlas_User_Service.dto.RegisterDTO;
import com.atlas.Atlas_User_Service.model.Administrador;
import com.atlas.Atlas_User_Service.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/atlas/adm")
public class AdminController{

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public ResponseEntity<List<Administrador>> findAll() {
        return ResponseEntity.ok(adminService.findAll());
    }

    @PostMapping
    public ResponseEntity<Administrador> create(@RequestBody RegisterDTO dto) {
        System.out.println("Recebido no controller: " + dto);
        Administrador savedAdmin = adminService.save(dto);
        return ResponseEntity.status((HttpStatus.CREATED)).body(savedAdmin);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        System.out.println("Deletando administrador com o id: " + id);
        adminService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



}
