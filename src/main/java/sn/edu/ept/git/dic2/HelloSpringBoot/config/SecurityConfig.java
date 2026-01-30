package sn.edu.ept.git.dic2.HelloSpringBoot.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import sn.edu.ept.git.dic2.HelloSpringBoot.filters.CustomSecurityFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomSecurityFilter customSecurityFilter;

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
                                .requestMatchers(HttpMethod.GET, "/employes").hasRole("EMPLOYE")
                                .requestMatchers(HttpMethod.GET, "/employes/{id}").hasRole("EMPLOYE")
                                .requestMatchers(HttpMethod.DELETE, "/employes/{id}").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/employes/{id}").hasRole("ADMIN")
                                .anyRequest().denyAll()
                )
                .addFilterBefore(customSecurityFilter, AuthorizationFilter.class)
                .build();
    }
}