package com.lvv.users_manager.helpers;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordHasherHelper {

    private final BCryptPasswordEncoder encoder;

    public PasswordHasherHelper() {
        this.encoder = new BCryptPasswordEncoder();
    }

    public String hashPassword(String password) {
        return this.encoder.encode(password);
    }

    public boolean checkPassword(String password, String hashedPassword) {
        return this.encoder.matches(password, hashedPassword);
    }

}