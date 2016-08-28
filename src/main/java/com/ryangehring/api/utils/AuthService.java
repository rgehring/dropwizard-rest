package com.ryangehring.api.utils;

import com.google.common.collect.ImmutableMap;
import io.dropwizard.auth.Auth;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.JwtContext;

import java.security.Principal;
import java.util.Map;
import java.util.Optional;

/**
 * Created by ryan on 8/27/16.
 */
public static class AuthService implements Authenticator<JwtContext, MyUser> {

    @Override
    public Optional<MyUser> authenticate(JwtContext context) {
        // Provide your own implementation to lookup users based on the principal attribute in the
        // JWT Token. E.g.: lookup users from a database etc.
        // This method will be called once the token's signature has been verified

        // In case you want to verify different parts of the token you can do that here.
        // E.g.: Verifying that the provided token has not expired.

        // All JsonWebTokenExceptions will result in a 401 Unauthorized response.

        try {
            final String subject = context.getJwtClaims().getSubject();
            if ("good-guy".equals(subject)) {
                return Optional.of(new MyUser(ONE, "good-guy"));
            }
            return Optional.empty();
        }
        catch (MalformedClaimException e) { return Optional.empty(); }
    }

    public Map<String, Object> get(@Auth Principal user) {
        return ImmutableMap.<String, Object> of ("username", user.getName(), "id", ((MyUser) user).getId());
    }

}
