package sn.edu.ept.git.dic2.HelloSpringBoot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sn.edu.ept.git.dic2.HelloSpringBoot.entities.Departement;

import java.util.List;
import java.util.Optional;

public interface DepartementRepository extends JpaRepository<Departement, Integer> {
    // return le departement ou null s'il ne le trouve pas
    //Departement findByNom(String nom);

    //Lorsque le resultat return au max 1 element
    Optional<Departement> findByNom(String nom);


    @Query("select d from Departement d where d.nom = :txt or d.chef.nom=:txt")
    List<Departement> search(@Param("txt") String txt);
}
