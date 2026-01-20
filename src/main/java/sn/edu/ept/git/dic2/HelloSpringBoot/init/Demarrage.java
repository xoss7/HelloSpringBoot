package sn.edu.ept.git.dic2.HelloSpringBoot.init;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"ept"})
public class Demarrage implements CommandLineRunner {

    @Value("${ept.git.contact.nom}")
    private String adminNom;

    @Value("${ept.git.contact.prenom}")
    private String adminPrenom;

    @Value("${ept.git.contact.email}")
    private String adminEmail;


    @Override
    public void run(String... args) throws Exception {
        System.out.println("Demarrage....");
        System.out.println("prenom: " + adminPrenom);
        System.out.println("nom: " + adminNom);
        System.out.println("email: " + adminEmail);

    }
}
