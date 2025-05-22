package com.atlas.Atlas_User_Service.service;

import com.atlas.Atlas_User_Service.dto.RegisterDTO;
import com.atlas.Atlas_User_Service.model.Administrador;
import com.atlas.Atlas_User_Service.repository.AdminRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder encoder = new BCryptPasswordEncoder();

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Administrador save(RegisterDTO dto) {
        Administrador a = new Administrador(dto.login(), encoder.encode(dto.password()), dto.role());
        return adminRepository.save(a);
    }

    public Administrador findByLogin(String login) {
        return adminRepository.findByLogin(login);
    }

    public void delete(Long id) {
        adminRepository.deleteById(id);
    }

    public List<Administrador> findAll() {
        return adminRepository.findAll();
    }


}
