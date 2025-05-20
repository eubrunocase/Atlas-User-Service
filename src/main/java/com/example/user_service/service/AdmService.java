package com.example.user_service.service;

import com.example.user_service.model.Administrador;
import com.example.user_service.repository.AdmRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdmService extends BaseService<Administrador> {

    private final AdmRepository admRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AdmService(AdmRepository admRepository) {
        super(admRepository); // passa o repositório específico para a superclasse
        this.admRepository = admRepository;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public Administrador save(Administrador administrador) {
        administrador.setPassword(bCryptPasswordEncoder.encode(administrador.getPassword()));
        return super.save(administrador);
    }

    public void deleteAll() {
        admRepository.deleteAll();
    }

    public Administrador findByLogin(String login) {
        return admRepository.findByLogin(login);
    }
}
