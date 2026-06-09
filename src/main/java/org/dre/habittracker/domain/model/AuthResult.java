package org.dre.habittracker.domain.model;

public class AuthResult {

    private Token token;
    private User user;

    public AuthResult(Token token, User user) {
        this.token = token;
        this.user = user;
    }

    public Token getToken() { return token; }
    public User getUser() { return user; }
}
