package com.autobestinfo.dev.core.security;

import com.autobestinfo.dev.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static com.autobestinfo.dev.core.security.SecurityUtils.*;
import static java.util.Collections.emptyList;

/**
 * JWT Authentication filter, Issue the token based on user login behavior
 *
 * @author Jiangqi Li
 */
//UsernamePasswordAuthenticationFilter This filter by default responds to the URL /login.
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;


    JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }



    //where we parse the user's credentials and issue them to the AuthenticationManager
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            User creds = new ObjectMapper()
                    .readValue(req.getInputStream(), User.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUsername(),
                            creds.getPassword(), //creds.getPassword(),
                            emptyList())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //which is the method called when a user successfully logs in. We use this method to generate a JWT for this user.
    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        org.springframework.security.core.userdetails.User prin  = (org.springframework.security.core.userdetails.User)auth.getPrincipal();
        String token = Jwts.builder()
                .setSubject(prin.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
                .compact();
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
        chain.doFilter(req, res);
    }
}
