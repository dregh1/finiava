package org.dre.habittracker.infrastructure.adapter.out.persistence;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import org.dre.habittracker.domain.model.User;
import org.dre.habittracker.domain.port.out.UserRepository;
import org.dre.habittracker.infrastructure.adapter.out.persistence.document.UserDocument;
import org.dre.habittracker.infrastructure.adapter.out.persistence.mapper.UserMapper;

import java.util.Optional;

@ApplicationScoped
public class UserRepositoryAdapter implements UserRepository, PanacheMongoRepository<UserDocument> {

    @Inject
    UserMapper userMapper;

    @Override
    public User save(User user) {
        UserDocument document = userMapper.toDocument(user);
        persist(document);
        return userMapper.toDomain(document);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return find("email", email)
                .firstResultOptional()
                .map(userMapper::toDomain);
    }

    @Override
    public Optional<User> findById(String id) {
        return findByIdOptional(new org.bson.types.ObjectId(id))
                .map(userMapper::toDomain);
    }

    @Override
    public boolean existsByEmail(String email) {
        return find("email", email).count() > 0;
    }
}
