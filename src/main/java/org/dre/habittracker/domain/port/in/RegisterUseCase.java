package org.dre.habittracker.domain.port.in;

import org.dre.habittracker.domain.model.AuthResult;
import org.dre.habittracker.domain.model.Token;

public interface RegisterUseCase {
    /**
     * Enregistre un nouvel utilisateur
     * @param name     nom de l'utilisateur
     * @param email    email de l'utilisateur
     * @param password mot de passe en clair
     * @return Token JWT généré
     */
    AuthResult register(String name, String email, String password);
}
