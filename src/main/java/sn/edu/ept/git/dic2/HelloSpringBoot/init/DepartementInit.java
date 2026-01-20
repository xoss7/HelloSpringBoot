package sn.edu.ept.git.dic2.HelloSpringBoot.init;

import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import sn.edu.ept.git.dic2.HelloSpringBoot.entities.Departement;
import sn.edu.ept.git.dic2.HelloSpringBoot.repositories.DepartementRepository;

import java.util.List;
import java.util.Optional;

@Component
@Order(1)
public class DepartementInit implements CommandLineRunner {

    private final DepartementRepository departementRepository;
    public DepartementInit(DepartementRepository departementRepository) {
        this.departementRepository = departementRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        System.out.println("init de departement");

        if(departementRepository.count() == 0) {
            ///  initialise
        }

        Optional<Departement> d1=departementRepository.findByNom("GIT");
        //si on a un resultat
        if(d1.isPresent()){
            Departement departement=d1.get();
        }

        // si pas de resultat
        if(d1.isEmpty()){

        }


        Departement dep1 = Departement.builder()
                .nom("GIT")
                .description("Génie Info et Télécom")
                .build();

        departementRepository.save(dep1);

        ///recuperer toutes les entités
         //List<Departement> departements = departementRepository.findAll();
        //supp toutes les entités
         //departementRepository.deleteAll();

        //upp toutes les entités sur la liste donnée en argument
        //departementRepository.deleteAll(departements);

        //enregistre la liste des entités en parametre
        //departementRepository.saveAll(departements);

        //trouver l'entité don l'id est 1
        //departementRepository.findById(1);


        departementRepository.save(
                Departement.builder()
                        .nom("GC")
                        .description("Génie Civil")
                        .build()
        );
    }
}
