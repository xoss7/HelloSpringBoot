package sn.edu.ept.git.dic2.HelloSpringBoot.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String login;
    private String password;
}
