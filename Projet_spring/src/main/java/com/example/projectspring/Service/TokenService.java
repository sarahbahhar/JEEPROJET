package com.example.projectspring.Service;


import com.example.projectspring.Repository.TokenRepository;
import com.mysql.cj.protocol.a.CompressedInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.projectspring.Entity.Token;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

@Service

public class TokenService {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int LENGTH = 10;
    private static SecureRandom random = new SecureRandom();

    @Autowired
    private TokenRepository tr;
    public void addToken(String email) {
        Token t = new Token();
        t.setEmail(email);
        t.setToken("0"); // Assurez-vous que cette logique de définition du token est correcte
        tr.save(t);
    }
    public void changeTokenByEmail(String email, String tokenValue) {
        tr.findByEmail(email).ifPresent(token -> {
            token.setToken(tokenValue);
            tr.save(token);
        });
    }
    public String getTokenValueByEmail(String email) {
        return tr.findByEmail(email)
                .map(Token::getToken)
                .orElse("error");
    }

    public static String generateResetToken() {
        StringBuilder token = new StringBuilder(LENGTH);
        for (int i = 0; i < LENGTH; i++) {
            token.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return token.toString();
    }

    public boolean isValidToken(String email, String resetToken){
        return (resetToken.equals(this.getTokenValueByEmail(email)));

    }





}
