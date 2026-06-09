package org.dre.habittracker.infrastructure.adapter.out.persistence.document;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

import java.time.LocalDateTime;

@MongoEntity(collection = "users")
public class UserDocument extends PanacheMongoEntity {

    public String name;
    public String email;
    public String password;
    public LocalDateTime createdAt;
    public LocalDateTime updatedAt;

    public UserDocument() {}
}
