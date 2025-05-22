package com.atlas.Atlas_User_Service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.spec.SecretKeySpec;


@Configuration
public class SecurityConfig {

    @Value("${api.security.token.secret}")
    private String secret;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST,"/internal/**").hasRole("INTERNAL")

                        .requestMatchers(HttpMethod.GET, "atlas/professores/").hasRole("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.POST, "atlas/administradores/").permitAll()
                        .requestMatchers(HttpMethod.GET, "atlas/adm/").hasRole("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.POST, "atlas/professores/").permitAll()
                        .anyRequest().authenticated()


                ) .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())))
                .build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withSecretKey(
                new SecretKeySpec(secret.getBytes(), "HmacSHA256")
        ).build();
    }


    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter () {
        var converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(new GrantedAuthoritiesExtractor());
        return converter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
