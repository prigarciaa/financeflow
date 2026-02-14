package com.priscila.financeflow.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.priscila.financeflow.security.JwtAuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    // Bean para criptografar senha
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Configuração de segurança
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())

                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .authorizeHttpRequests(auth -> auth
                        // Rotas públicas
                        .requestMatchers("/auth/**", "/health").permitAll()
                        .requestMatchers(HttpMethod.POST,"/users/**").permitAll()

                        // Swagger
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/v3/api-docs",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll()

                        .anyRequest().authenticated()
                )
                // desativa login padrão do spring
                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable())
                .anonymous(anon -> anon.disable())
                // adiciona o filtro JWT
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}

