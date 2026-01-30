package sn.edu.ept.git.dic2.HelloSpringBoot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.edu.ept.git.dic2.HelloSpringBoot.entities.Compte;

import java.util.Optional;

@Repository
public interface CompteRepository extends JpaRepository<Compte, Integer> {
    Optional<Compte> findByLogin(String login);
}
