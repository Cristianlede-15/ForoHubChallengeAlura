package com.ForoHub.demo.Controller;

import com.ForoHub.demo.Repositories.Response;
import com.ForoHub.demo.Services.ResponseSer;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Response")
public class Responses {
    @Autowired
    private ResponseSer responseService;

    @GetMapping
    public ResponseEntity<List<com.ForoHub.demo.Dtos.Responses>> getAllResponses() {
        List<com.ForoHub.demo.Dtos.Responses> responseDTOs = responseService.getAllResponses();
        return new ResponseEntity<>(responseDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<com.ForoHub.demo.Dtos.Responses> getResponseById(@PathVariable Long id) {
        try {
            com.ForoHub.demo.Dtos.Responses responseDTO = responseService.getResponseById(id);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/{id}")
    public ResponseEntity<com.ForoHub.demo.Dtos.Responses> createResponse(@PathVariable Long id,@RequestBody ResponseDTO responseDTO) {
        com.ForoHub.demo.Dtos.Responses newResponse = responseService.createResponse(id, responseDTO);
        return new ResponseEntity<>(newResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<com.ForoHub.demo.Dtos.Responses> updateResponse(@PathVariable Long id, @RequestBody ResponseDTO responseDTO) {
        try {
            com.ForoHub.demo.Dtos.Responses updatedResponse = responseService.updateResponse(id, responseDTO);
            return new ResponseEntity<>(updatedResponse, HttpStatus.OK);
        } catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResponse(@PathVariable Long id) {
        boolean isDeleted = responseService.deleteResponse(id);
        if (!isDeleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
