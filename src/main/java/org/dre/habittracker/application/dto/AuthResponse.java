package org.dre.habittracker.application.dto;

public class AuthResponse {
    private String accessToken;
    private String tokenType;
    private long expiresIn;
    private UserInfo user;

    public AuthResponse() {}

    public static AuthResponse of(String accessToken, long expiresIn, UserInfo user) {
        AuthResponse response = new AuthResponse();
        response.accessToken = accessToken;
        response.tokenType = "Bearer";
        response.expiresIn = expiresIn;
        response.user = user;
        return response;
    }

    public String getAccessToken() { return accessToken; }
    public void setAccessToken(String accessToken) { this.accessToken = accessToken; }

    public String getTokenType() { return tokenType; }
    public void setTokenType(String tokenType) { this.tokenType = tokenType; }

    public long getExpiresIn() { return expiresIn; }
    public void setExpiresIn(long expiresIn) { this.expiresIn = expiresIn; }

    public UserInfo getUser() { return user; }
    public void setUser(UserInfo user) { this.user = user; }

    public static class UserInfo {

        private String id;
        private String name;
        private String email;

        public UserInfo() {}

        public static UserInfo of(String id, String name, String email) {
            UserInfo info = new UserInfo();
            info.id = id;
            info.name = name;
            info.email = email;
            return info;
        }

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
    }
}
