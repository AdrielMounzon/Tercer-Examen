package org.example.prueba2;

public class TokenDto {
    private String token;
    private long timestamp;

    public TokenDto(String token, long timestamp) {
        this.token = token;
        this.timestamp = timestamp;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
