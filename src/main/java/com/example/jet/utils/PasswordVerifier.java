package com.example.jet.utils;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public class PasswordVerifier {
    public static boolean verifyPassword(String hash, String password) {
        Argon2 argon2 = Argon2Factory.create();
        try {
            return argon2.verify(hash, password);
        } finally {
            argon2.wipeArray(password.toCharArray());
        }
    }
}
