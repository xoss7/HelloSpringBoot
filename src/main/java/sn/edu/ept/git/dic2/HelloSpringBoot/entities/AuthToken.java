package sn.edu.ept.git.dic2.HelloSpringBoot.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class AuthToken {

    @Id
    private String token;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Compte compte;

    @Column(nullable = false)
    private LocalDateTime notBefore;

    @Column(nullable = false)
    private LocalDateTime notAfter;

    private Boolean revoked;
}
