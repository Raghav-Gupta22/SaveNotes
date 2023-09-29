package com.nagarro.notesapp.controllers;

import com.nagarro.notesapp.config.JwtService;
import com.nagarro.notesapp.impl.UserDetailsServiceImpl;
import com.nagarro.notesapp.models.JwtRequest;
import com.nagarro.notesapp.models.JwtResponse;
import com.nagarro.notesapp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
 public class JwtController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl detailsServiceImpl;

    @Autowired
    private JwtService jwtService;
    @CrossOrigin("*")
    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {
            authenticate(jwtRequest.getUserName(), jwtRequest.getPassword());
        } catch (UsernameNotFoundException e) {
            throw new Exception("User not found");
        }
        UserDetails userDetails = this.detailsServiceImpl.loadUserByUsername(jwtRequest.getUserName());
        String s = this.jwtService.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(s));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("User Disable");
        } catch (BadCredentialsException e) {
            throw new Exception("Bad Credentials!!");
        }
    }

    @GetMapping("/current-user")
    @CrossOrigin("*")
    public User getCurrentUser(Principal principal) {
        return ((User) this.detailsServiceImpl.loadUserByUsername(principal.getName()));
    }
}
