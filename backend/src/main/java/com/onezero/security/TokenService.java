package com.onezero.security;

import com.onezero.model.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class TokenService {
    private final JwtEncoder accessTokenEncoder;
    @Qualifier("jwtRefreshTokenEncoder")
    private final JwtEncoder refreshTokenEncoder;
    private String createAccessToken(Authentication authentication) {
        SecurityUser user = (SecurityUser) authentication.getPrincipal();
        Instant now = Instant.now().plusMillis(TimeUnit.HOURS.toMillis(8));
        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .issuer("myApp")
                .issuedAt(now)
                .expiresAt(now.plus(5, ChronoUnit.MINUTES))
                .subject(user.user().getUsername())
                .claim("userId", user.user().getId())
                .build();

        return accessTokenEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
    }

    private String createRefreshToken(Authentication authentication) {
        SecurityUser user = (SecurityUser) authentication.getPrincipal();
        Instant now = Instant.now().plusMillis(TimeUnit.HOURS.toMillis(8));

        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .issuer("myApp")
                .issuedAt(now)
                .expiresAt(now.plus(30, ChronoUnit.DAYS))
                .subject(user.user().getUsername())
                .claim("userId", user.user().getId())
                .build();

        return refreshTokenEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
    }

    public TokenResponse createToken(Authentication authentication) {
        if (!(authentication.getPrincipal() instanceof SecurityUser user)) {
            throw new BadCredentialsException(
                    MessageFormat.format("principal {0} is not of User type", authentication.getPrincipal().getClass())
            );
        }

        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setUserId(user.user().getId());
        tokenResponse.setToken(createAccessToken(authentication));

        String refreshToken;
        if (authentication.getCredentials() instanceof Jwt jwt) {
            Instant now = Instant.now().plusMillis(TimeUnit.HOURS.toMillis(8));
            Instant expiresAt = jwt.getExpiresAt();
            Duration duration = Duration.between(now, expiresAt);
            long daysUntilExpired = duration.toDays();
            if (daysUntilExpired < 7) {
                refreshToken = createRefreshToken(authentication);
            } else {
                refreshToken = jwt.getTokenValue();
            }
        } else {
            refreshToken = createRefreshToken(authentication);
        }
        tokenResponse.setRefreshToken(refreshToken);

        return tokenResponse;
    }
}
