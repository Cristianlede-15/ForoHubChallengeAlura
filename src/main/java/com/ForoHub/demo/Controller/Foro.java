package com.ForoHub.demo.Controller;

import com.ForoHub.demo.Domain.Users.UsersForo;
import com.ForoHub.demo.Services.UserForoSer;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/User")
public class Foro {
    @Autowired
    private UserForoSer userService;

    @GetMapping
    public ResponseEntity<List<UsersForo>> getAllUsers() {
        List<UsersForo> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserForo> getUserById(@PathVariable Long id) {
        try {
            UsersForo user = userService.getUserById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<UserForo> createUser(@RequestBody UserForoDTO dto) {
        UsersForo createdUser = userService.createUser(dto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserForo> updateUser(@PathVariable Long id, @RequestBody UserForoDTO dto){
        try {
            UsersForo userUpdated = userService.updateUser(id, dto);
            return new ResponseEntity<>(userUpdated, HttpStatus.OK);
        }catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
