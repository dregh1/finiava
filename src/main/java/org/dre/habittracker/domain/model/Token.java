package org.dre.habittracker.domain.model;

public class Token {

    private String accessToken;
    private String tokenType;
    private long expiresIn; // en secondes

    public Token() {}

    public static Token of(String accessToken) {
        Token token = new Token();
        token.accessToken = accessToken;
        token.tokenType = "Bearer";
        token.expiresIn = 86400; // 24h
        return token;
    }

    public String getAccessToken() { return accessToken; }
    public void setAccessToken(String accessToken) { this.accessToken = accessToken; }

    public String getTokenType() { return tokenType; }
    public void setTokenType(String tokenType) { this.tokenType = tokenType; }

    public long getExpiresIn() { return expiresIn; }
    public void setExpiresIn(long expiresIn) { this.expiresIn = expiresIn; }
}