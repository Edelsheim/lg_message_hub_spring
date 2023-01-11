package com.example.msghub.api.auth.controller;

import com.example.msghub.common.http.WebClientResponse;
import com.example.msghub.common.util.Auth;
import com.example.msghub.api.auth.dto.AuthDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/msghub/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping("/{key}")
    public ResponseEntity<AuthDto.AuthResponse> getAuth(
        @RequestParam String key
    ) {
        if (userCode != null && !userCode.isBlank()) {
            WebClientResponse<AuthDto.AuthResponse> result = this.authService.getAuth(userCode);
            return ResponseEntity.status(result.statusCode()).body(result.body());
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthDto.AuthResponse(
                            String.valueOf(HttpStatus.UNAUTHORIZED.value()),
                            "Get Token Fail",
                            null)
                    );
        }
    }

    @PutMapping("/refresh")
    public ResponseEntity<AuthDto.RefreshResponse> refreshToken(
        @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false, defaultValue = "") String authToken
    ) {
        if (Auth.validBearer(authToken)) {
            String token = Auth.getBearerToken(authToken);
            WebClientResponse<AuthDto.RefreshResponse> result = this.authService.getRefresh(token);
            return ResponseEntity.status(result.statusCode())
                    .body(result.body());
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthDto.RefreshResponse(
                            String.valueOf(HttpStatus.UNAUTHORIZED.value()),
                            "Valid Token Fail",
                            null)
                    );
            }
        }
}
