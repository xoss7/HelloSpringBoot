package sn.edu.ept.git.dic2.HelloSpringBoot.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String login;

    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    private Employe employe;
}
