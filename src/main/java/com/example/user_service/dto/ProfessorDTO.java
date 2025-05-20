package com.example.user_service.dto;

import com.example.user_service.model.enums.UserRoles;
import lombok.Data;

@Data
public class ProfessorDTO {
    private Long id;
    private String login;
    private UserRoles role;
}
