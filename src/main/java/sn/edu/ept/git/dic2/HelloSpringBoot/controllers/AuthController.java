package sn.edu.ept.git.dic2.HelloSpringBoot.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.edu.ept.git.dic2.HelloSpringBoot.dto.AuthRequest;
import sn.edu.ept.git.dic2.HelloSpringBoot.dto.AuthResponse;
import sn.edu.ept.git.dic2.HelloSpringBoot.dto.RegisterRequest;
import sn.edu.ept.git.dic2.HelloSpringBoot.services.CompteService;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final CompteService compteService;

    public AuthController(CompteService compteService) {
        this.compteService = compteService;
    }

    @PutMapping("/register")
    public void register(@RequestBody RegisterRequest request) {
        compteService.createCompte(request.getLogin(), request.getPassword());
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        Optional<AuthResponse> token = compteService.authenticateUser(
                request.getLogin(), request.getPassword());

        return token.map(ResponseEntity::ok).orElseGet(
                () -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
}