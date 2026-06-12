package org.dre.habittracker.infrastructure.adapter.out.sercurity;

import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import org.dre.habittracker.domain.model.User;
import org.dre.habittracker.domain.port.out.TokenGenerator;

import java.time.Duration;

@ApplicationScoped
public class JwtTokenGenerator implements TokenGenerator {
    @Override
    public String generate(User user) {
        return Jwt.issuer("${mp.jwt.verify.issuer}")
                .subject(user.getId())
                .claim("email", user.getEmail())
                .claim("name", user.getName())
                .groups("user")
                .expiresIn(Duration.ofHours(24))
                .sign();
    }
}
