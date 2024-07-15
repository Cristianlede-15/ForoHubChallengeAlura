package com.ForoHub.demo.Controller;

import com.ForoHub.demo.Dtos.TokenJWT;
import com.ForoHub.demo.Domain.Users.UsersApi;
import com.ForoHub.demo.Dtos.UserAuthentication;
import com.ForoHub.demo.Infraestructure.Security.TokenSer;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class Authentication {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenSer tokenService;

    @PostMapping
    public ResponseEntity authenticateUser(@RequestBody @Valid UserAuthentication authenticationUserDTO){
        org.springframework.security.core.Authentication authentication = new UsernamePasswordAuthenticationToken(authenticationUserDTO.login(), authenticationUserDTO.key());
        var authenticatedUser = authenticationManager.authenticate(authentication);
        var jwtToken = tokenService.generateToken((UsersAPI) authenticatedUser.getPrincipal());
        return ResponseEntity.ok(new TokenJWT(jwtToken));
    }
}
