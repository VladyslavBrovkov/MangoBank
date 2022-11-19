package com.example.mangobank.controller;

import com.example.mangobank.jwtutil.JwtUserDetailsService;
import com.example.mangobank.jwtutil.TokenManager;
import com.example.mangobank.model.dto.JwtRequestModel;
import com.example.mangobank.model.dto.JwtResponseModel;
import com.example.mangobank.model.entity.User;
import com.example.mangobank.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class LoginJwtController {

    @Autowired
    private JwtUserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenManager tokenManager;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> createToken(@RequestBody JwtRequestModel request) throws Exception {
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
        final User userForToken = userService.findByEmail(userDetails.getUsername());
        final String jwtToken = tokenManager.generateJwtToken(userDetails, userForToken,false);
        final String jwtTokenCookie = tokenManager.generateJwtToken(userDetails, userForToken,true);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Set-Cookie", "jwt=" + jwtTokenCookie + "; Max-Age=6000; Path=/; SameSite=None; Secure; HttpOnly");
        return ResponseEntity.ok()
                .headers(headers)
                .body(new JwtResponseModel(jwtToken));
    }

    @GetMapping("/refresh")
    public ResponseEntity<?> refreshToken(@CookieValue("jwt") String token) {
        String userName = "";
        try {
            tokenManager.validateJwtToken(token);
            userName = tokenManager.getUsernameFromToken(token);
        } catch (UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            System.out.println(e);
            return new ResponseEntity<>("UNAUTHORIZED", HttpStatus.UNAUTHORIZED);
        } catch (ExpiredJwtException e) {
            System.out.println("Token was expired");
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        final User userForToken = userService.findByEmail(userDetails.getUsername());
        String jwtToken = tokenManager.generateJwtToken(userDetails, userForToken,false);
        return ResponseEntity.ok(new JwtResponseModel(jwtToken));
    }


    @GetMapping("/getClaims")  //todo: for testing User
    public String getClaims(@RequestHeader("Authorization") String token) {
        return tokenManager.getClaimsFromToken(token.substring(7));
    }
}

