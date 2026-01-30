package sn.edu.ept.git.dic2.HelloSpringBoot.dto;

import lombok.Data;
import sn.edu.ept.git.dic2.HelloSpringBoot.entities.AuthToken;

import java.time.LocalDateTime;

@Data
public class AuthResponse {
    private String token;
    private String login;
    private LocalDateTime notBefore;
    private LocalDateTime notAfter;

    public static AuthResponse from(AuthToken token) {
        AuthResponse response = new AuthResponse();
        response.setToken(token.getToken());
        response.setLogin(token.getCompte().getLogin());
        response.setNotBefore(token.getNotBefore());
        response.setNotAfter(token.getNotAfter());
        return response;
    }
}
