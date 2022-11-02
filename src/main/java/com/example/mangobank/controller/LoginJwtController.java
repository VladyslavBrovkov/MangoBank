package com.example.mangobank.controller;

import com.example.mangobank.jwtutil.JwtUserDetailsService;
import com.example.mangobank.jwtutil.TokenManager;
import com.example.mangobank.model.dto.JwtRequestModel;
import com.example.mangobank.model.dto.JwtResponseModel;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class LoginJwtController {

    @Autowired
    private JwtUserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenManager tokenManager;

    @PostMapping("/login")
    public ResponseEntity<?> createToken(@RequestBody JwtRequestModel request, HttpServletResponse response) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(),
                            request.getPassword()));
        } catch (DisabledException e) {
            throw new Exception("User disabled", e); //todo: custom exception create
        } catch (BadCredentialsException e) {
            throw new Exception("Invalid credentials", e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        final String jwtToken = tokenManager.generateJwtToken(userDetails);
        Cookie cookie = new Cookie("jwt", jwtToken);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return ResponseEntity.ok(new JwtResponseModel(jwtToken));
    }

    @GetMapping("/refresh")
    public ResponseEntity<?> refreshToken(@CookieValue("jwt") String token) throws Exception {
        String userName = "";
        if (!tokenManager.validateJwtToken(token)) {
            try {
                userName = tokenManager.getUsernameFromToken(token);

            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired");
            }
            final UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
            final String jwtToken = tokenManager.generateJwtToken(userDetails);
            return ResponseEntity.ok(new JwtResponseModel(jwtToken));
        }
        return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
    }


    @GetMapping("/getClaims")  //todo: for testing User
    public String getClaims(@RequestHeader("Authorization") String token) {
        return tokenManager.getClaimsFromToken(token.substring(7));
    }
}

