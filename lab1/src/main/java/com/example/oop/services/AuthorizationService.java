package com.example.oop.services;

import org.keycloak.TokenVerifier;
import org.keycloak.common.VerificationException;
import org.keycloak.representations.AccessToken;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

public class AuthorizationService {
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";

    public boolean notAuthorised(HttpServletRequest request, String authority) {
        String authorization = request.getHeader(AUTHORIZATION_HEADER);
        if (authorization == null) {
            return true;
        }
        String tokenString = authorization.substring(BEARER_PREFIX.length());
        TokenVerifier<AccessToken> verifier = TokenVerifier.create(tokenString, AccessToken.class);
        AccessToken token;
        try {
            token = verifier.getToken();
        } catch (VerificationException e) {
            return true;
        }
        Set<String> roles = token.getRealmAccess().getRoles();
        return !roles.contains(authority);
    }

    public String getUsername(HttpServletRequest request) {
        String authorization = request.getHeader(AUTHORIZATION_HEADER);
        String tokenString = authorization.substring(BEARER_PREFIX.length());
        try {
            AccessToken token = TokenVerifier.create(tokenString, AccessToken.class).getToken();
            return token.getPreferredUsername();
        } catch (VerificationException e) {
            throw new RuntimeException(e);
        }
    }
}
