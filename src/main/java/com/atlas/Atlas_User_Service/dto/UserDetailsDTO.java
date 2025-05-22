package com.atlas.Atlas_User_Service.dto;

import com.atlas.Atlas_User_Service.model.UserRoles;
import com.fasterxml.jackson.annotation.JsonProperty;

public record UserDetailsDTO(@JsonProperty("login") String login,
                             @JsonProperty("password") String password,
                             @JsonProperty("escola") String escola,
                             @JsonProperty("role") UserRoles role) {
}
