package com.ForoHub.demo.Controller;

import com.ForoHub.demo.Domain.Topics.Topic;
import com.ForoHub.demo.Dtos.TopicDtos;
import com.ForoHub.demo.Erros.ForoExceptions;
import com.ForoHub.demo.Services.TopicSer;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Topics")
public class Topics {
    @Autowired
    public TopicSer topicService;
    @GetMapping
    public ResponseEntity<List<TopicDtos>> getAllTopics() {
        List<TopicDtos> topicsDTO = topicService.getAllTopics();
        return new ResponseEntity<>(topicsDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicDtos> getTopicById(@PathVariable Long id) {
        try {
            TopicDtos topicDTO = topicService.getTopicById(id);
            return new ResponseEntity<>(topicDTO, HttpStatus.OK);
        } catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<TopicDtos> createTopic(@RequestBody Topic dto) {
        try {
            TopicDtos createdTopic = topicService.createTopic(dto);
            return new ResponseEntity<>(createdTopic, HttpStatus.CREATED);
        } catch (ForoExceptions e) {
            return new ResponseEntity<>(e.getStatus());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicDtos> updateTopic(@PathVariable Long id, @RequestBody Topic dto) {
        try {
            TopicDtos updatedTopic = topicService.updateTopic(id, dto);
            return new ResponseEntity<>(updatedTopic, HttpStatus.OK);
        } catch (ForoExceptions e) {
            return new ResponseEntity<>(e.getStatus());
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {
        topicService.deleteTopic(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
