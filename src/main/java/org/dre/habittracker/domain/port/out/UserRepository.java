package org.dre.habittracker.domain.port.out;

import org.dre.habittracker.domain.model.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);

    Optional<User> findByEmail(String email);

    Optional<User> findById(String id);

    boolean existsByEmail(String email);
}
