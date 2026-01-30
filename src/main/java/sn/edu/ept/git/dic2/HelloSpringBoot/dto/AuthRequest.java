package sn.edu.ept.git.dic2.HelloSpringBoot.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String login;
    private String password;
}
