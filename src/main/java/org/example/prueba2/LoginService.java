package org.example.prueba2;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LoginService {

    public TokenDto authenticate(LoginDto loginDto) {

        String token = generateToken();
        long timestamp = System.currentTimeMillis();

        return new TokenDto(token, timestamp);
    }

    private String generateToken() {

        return UUID.randomUUID().toString();
    }
}

