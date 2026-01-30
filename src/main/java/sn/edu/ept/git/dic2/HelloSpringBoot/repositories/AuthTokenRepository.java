package sn.edu.ept.git.dic2.HelloSpringBoot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.edu.ept.git.dic2.HelloSpringBoot.entities.AuthToken;

public interface AuthTokenRepository extends JpaRepository<AuthToken, String> {
}
