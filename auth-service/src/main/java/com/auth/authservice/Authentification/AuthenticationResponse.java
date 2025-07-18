package com.auth.authservice.Authentification;

import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@Builder
public class AuthenticationResponse {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public AuthenticationResponse() {}

}
