package sn.edu.ept.git.dic2.HelloSpringBoot.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import sn.edu.ept.git.dic2.HelloSpringBoot.entities.Employe;
import sn.edu.ept.git.dic2.HelloSpringBoot.services.EmployeService;

import java.util.Collection;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/employes")
//@CrossOrigin(origins = "http://localhost:4200")
public class EmployeController {

    private final EmployeService employeService;

    public EmployeController(EmployeService employeService){
        this.employeService = employeService;
    }

    @GetMapping
    public List<Employe> findAll(Authentication authentication){

        // Véfifier si le user s'est connecté
        if(authentication == null){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        Object principal = authentication.getPrincipal();
        if(principal instanceof Employe){
            Employe employe = (Employe) principal;
            log.info("Nom Employé={}", employe.getPrenom());
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        Object credentials = authentication.getCredentials();
        log.info("Credentials={}", credentials);

        // Récupération des roles
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        for(GrantedAuthority grantedAuthority : authorities){
            log.info("Droits={}", grantedAuthority.getAuthority());
        }


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return employeService.findAll();
    }

    @GetMapping("/{id}")
    public Employe findById(@PathVariable(name = "id") Integer idEmploye){
        return employeService.findById(idEmploye);
    }

    @PutMapping
    public ResponseEntity<Employe> save(@RequestBody Employe employe){
        if (employe.getNom() == null || employe.getNom().isBlank()){
            return ResponseEntity.status(451).build();
        }
        Employe result = employeService.save(employe);

        return ResponseEntity.status(201)
                .body(result);
    }

    @PutMapping("/{id}")
    public Employe update(
            @PathVariable(name = "id") Integer id,
            @RequestBody Employe employe){
        return employeService.save(employe);
    }

}
