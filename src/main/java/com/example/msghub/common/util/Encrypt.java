package com.example.msghub.common.util;

import jakarta.validation.constraints.NotNull;
import org.springframework.util.Base64Utils;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encrypt {
    public static byte[] SHA512(byte[] utf8Key) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.reset();
        md.update(utf8Key);
        return md.digest();
    }

    public static byte[] SHA512(@NotNull String key) throws NoSuchAlgorithmException {
        return Encrypt.SHA512(key.getBytes(StandardCharsets.UTF_8));
    }

    public static String SHA512ToString(@NotNull String key) throws NoSuchAlgorithmException {
        return Encrypt.SHA512ToString(key.getBytes(StandardCharsets.UTF_8));
    }

    public static String SHA512ToString(byte[] utf8Key) throws NoSuchAlgorithmException {
        return Base64Utils.encodeToString(Encrypt.SHA512(utf8Key));
    }
}
