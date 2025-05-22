package com.atlas.Atlas_User_Service.dto;

import com.atlas.Atlas_User_Service.model.UserRoles;

public record RegisterDTO(String login, String password, String escola, UserRoles role) {
}
