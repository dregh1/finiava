package org.dre.habittracker.domain.port.in;

import org.dre.habittracker.domain.model.AuthResult;
import org.dre.habittracker.domain.model.Token;

public interface LoginUseCase {
    /**
     * Authentifie un utilisateur
     * @param email    email de l'utilisateur
     * @param password mot de passe en clair
     * @return Token JWT généré
     */
    AuthResult login(String email, String password);
}
