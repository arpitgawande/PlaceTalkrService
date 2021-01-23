package com.placetalkr.prototype.controller;

import com.placetalkr.prototype.dto.AuthRequest;
import com.placetalkr.prototype.dto.UserDto;
import com.placetalkr.prototype.model.User;
import com.placetalkr.prototype.repository.UserRepository;
import com.placetalkr.prototype.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
/**
 * Controller to register user and generate token after authentication
 */
public class JwtAuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UserRepository userRepository;

    /**
     * Register a new user
     * @param userDto
     * @return User details
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDto userDto) {
        try{
            final UserDetails userDetails = userDetailsService
                    .loadUserByUsername(userDto.getUsername());
        } catch (UsernameNotFoundException e){
            User user = new User.UserBuilder()
                    .id(userDto.getId())
                    .username(userDto.getPassword())
                    .password(userDto.getPassword())
                    .firstname(userDto.getFirstname())
                    .lastname(userDto.getLastname())
                    .build();
            userRepository.save(user);
            return new ResponseEntity<>("User created with Id:" + user.getId(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Username Already exist", HttpStatus.BAD_REQUEST);
    }

    /**
     * Authenticate the user
     * @param authRequest
     * @return String : authentication token
     */
    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody AuthRequest authRequest){
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authRequest.getUsername());
        if(userDetails != null){
            final String token = JwtUtil.generateToken(userDetails.getUsername());
            return ResponseEntity.ok(token);
        } else{
            return new ResponseEntity<String>("User not found", HttpStatus.UNAUTHORIZED);
        }
    }
}
