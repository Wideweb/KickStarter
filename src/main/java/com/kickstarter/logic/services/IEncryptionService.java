package com.kickstarter.logic.services;

import java.security.NoSuchAlgorithmException;

public interface IEncryptionService {
    String createPasswordHash(String passwordToHash, String salt);
    String createSalt() throws NoSuchAlgorithmException;
}
