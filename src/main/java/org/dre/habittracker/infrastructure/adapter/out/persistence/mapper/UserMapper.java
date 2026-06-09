package org.dre.habittracker.infrastructure.adapter.out.persistence.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import org.dre.habittracker.domain.model.User;
import org.dre.habittracker.infrastructure.adapter.out.persistence.document.UserDocument;


@ApplicationScoped
public class UserMapper {

    // Domain → Document
    public UserDocument toDocument(User user) {
        UserDocument document = new UserDocument();
        document.name = user.getName();
        document.email = user.getEmail();
        document.password = user.getPassword();
        document.createdAt = user.getCreatedAt();
        document.updatedAt = user.getUpdatedAt();
        return document;
    }

    // Document → Domain
    public User toDomain(UserDocument document) {
        return new User(
                document.id.toString(),
                document.name,
                document.email,
                document.password,
                document.createdAt,
                document.updatedAt
        );
    }
}
