package org.dre.habittracker.domain.port.out;

import org.dre.habittracker.domain.model.User;

public interface TokenGenerator {
    /**
     * Génère un JWT pour un utilisateur
     * @param user utilisateur authentifié
     * @return token JWT signé
     */
    String generate(User user);
}
