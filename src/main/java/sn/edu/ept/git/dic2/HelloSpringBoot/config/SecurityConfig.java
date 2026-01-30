package sn.edu.ept.git.dic2.HelloSpringBoot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                        authorizeRequests -> authorizeRequests
                                .requestMatchers("/help/**").permitAll()
                                .requestMatchers("/swagger-ui/**").permitAll()
                                .requestMatchers("/v3/api-docs/**").permitAll()
                                .requestMatchers("/swagger-ui").permitAll()
                                .requestMatchers(HttpMethod.GET, "/employes").hasRole("EMPLOYEE")
                                .requestMatchers(HttpMethod.GET, "/employes/{id}").hasRole("EMPLOYEE")
                                .requestMatchers(HttpMethod.DELETE, "/employes/{id}").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/employes/{id}").hasRole("ADMIN")
                                .anyRequest().denyAll()
                )
                .build();
    }
}