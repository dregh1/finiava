package org.dre.habittracker.domain.service;

import jakarta.inject.Inject;
import org.dre.habittracker.domain.model.AuthResult;
import org.dre.habittracker.domain.model.Token;
import org.dre.habittracker.domain.model.User;
import org.dre.habittracker.domain.port.in.LoginUseCase;
import org.dre.habittracker.domain.port.in.RegisterUseCase;
import org.dre.habittracker.domain.port.out.TokenGenerator;
import org.dre.habittracker.domain.port.out.UserRepository;

public class AuthService implements RegisterUseCase, LoginUseCase {
    private final UserRepository userRepository;
    private final TokenGenerator tokenGenerator;

    @Inject
    public AuthService(UserRepository userRepository, TokenGenerator tokenGenerator) {
        this.userRepository = userRepository;
        this.tokenGenerator = tokenGenerator;
    }

    @Override
    public AuthResult register(String name, String email, String password) {

        // 1. Vérifier si l'email existe déjà
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email déjà utilisé : " + email);
        }

        // 2. Hasher le mot de passe
        String hashedPassword = hashPassword(password);

        // 3. Créer l'utilisateur
        User user = User.create(name, email, hashedPassword);

        // 4. Sauvegarder
        User savedUser = userRepository.save(user);

        // 5. Générer le token
        String jwt = tokenGenerator.generate(savedUser);

        Token token = Token.of(jwt);

        return new AuthResult(token, savedUser);
    }

    @Override
    public AuthResult login(String email, String password) {

        // 1. Trouver l'utilisateur par email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Email ou mot de passe incorrect"));

        // 2. Vérifier le mot de passe
        if (!verifyPassword(password, user.getPassword())) {
            throw new IllegalArgumentException("Email ou mot de passe incorrect");
        }

        // 3. Générer le token
        String jwt = tokenGenerator.generate(user);

        Token token = Token.of(jwt);

        return new AuthResult(token, user);
    }

    private String hashPassword(String password) {
        return org.mindrot.jbcrypt.BCrypt.hashpw(password, org.mindrot.jbcrypt.BCrypt.gensalt());
    }

    private boolean verifyPassword(String rawPassword, String hashedPassword) {
        return org.mindrot.jbcrypt.BCrypt.checkpw(rawPassword, hashedPassword);
    }

}
