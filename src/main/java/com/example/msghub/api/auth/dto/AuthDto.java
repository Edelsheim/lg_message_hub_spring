package com.example.msghub.api.auth.dto;

public class AuthDto {
    public record AuthRequest(String apiKey, String apiPwd) {}
    public record AuthResponse(String code, String message, AuthResult data) {}
    public record AuthResult(String token, String refreshToken) {}

    public record RefreshRequest() {}
    public record RefreshResponse(String code, String message, RefreshResult data) {}
    public record RefreshResult(String token) {}
}
