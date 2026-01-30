package sn.edu.ept.git.dic2.HelloSpringBoot.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sn.edu.ept.git.dic2.HelloSpringBoot.dto.AuthResponse;
import sn.edu.ept.git.dic2.HelloSpringBoot.entities.AuthToken;
import sn.edu.ept.git.dic2.HelloSpringBoot.entities.Compte;
import sn.edu.ept.git.dic2.HelloSpringBoot.repositories.AuthTokenRepository;
import sn.edu.ept.git.dic2.HelloSpringBoot.repositories.CompteRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CompteService {

    private final PasswordEncoder passwordEncoder;
    private final CompteRepository compteRepository;
    private final AuthTokenRepository authTokenRepository;

    @Value("${ept.git.auth.token.char}")
    private String tokenChars;

    @Value("${ept.git.auth.token.length}")
    private Long tokenLength;

    @Value("${ept.git.auth.token.duration}")
    private Long tokenDuration;

    public void createCompte(String login, String password) {

        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException();
        }

        if (login == null || login.isBlank()) {
            throw new IllegalArgumentException();
        }

        Compte nouveauCompte = new Compte();
        nouveauCompte.setLogin(login);
        nouveauCompte.setPassword(passwordEncoder.encode(password));
        compteRepository.save(nouveauCompte);
    }

    public Optional<AuthResponse> authenticateUser(String login, String password) {
        Optional<Compte> compte = compteRepository.findByLogin(login);

        if (compte.isPresent()) {
            if (passwordEncoder.matches(password, compte.get().getPassword())) {
                return Optional.of(AuthResponse.from(genToken(compte.get())));
            }
        }
        return Optional.empty();
    }

    public AuthToken genToken(Compte compte) {
        AuthToken token = new AuthToken();
        token.setCompte(compte);
        token.setToken(genTokenChar());
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expired = now.plusSeconds(tokenDuration);
        token.setNotBefore(now);
        token.setNotAfter(expired);
        token.setRevoked(false);
        return token;
    }

    private String genTokenChar() {

        while (true) {

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < tokenLength; i++) {
                int position = (int) (Math.random() * tokenChars.length());
                sb.append(tokenChars.charAt(position));
            }

            String token = sb.toString();
            Optional<AuthToken> tokenDB = authTokenRepository.findById(token);

            if (tokenDB.isEmpty()) {
                return token;
            }
        }
    }
}