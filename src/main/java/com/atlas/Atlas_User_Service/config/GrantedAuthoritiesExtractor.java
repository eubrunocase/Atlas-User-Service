package com.atlas.Atlas_User_Service.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;
import java.util.List;

public class GrantedAuthoritiesExtractor implements Converter<Jwt, Collection<GrantedAuthority>> {


    public Collection<GrantedAuthority> convert(Jwt jwt) {
        System.out.println(">>> TOKEN JWT ROLE: " + jwt.getClaimAsString("role"));
        String role = jwt.getClaimAsString("role");
        if (role == null) return List.of();

        return List.of(new SimpleGrantedAuthority("ROLE_" + role));
    }
}

