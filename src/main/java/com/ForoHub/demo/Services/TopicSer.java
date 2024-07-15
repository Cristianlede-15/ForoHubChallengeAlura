package com.ForoHub.demo.Services;

import com.ForoHub.demo.Dtos.TopicDtos;
import com.ForoHub.demo.Erros.ForoExceptions;
import com.ForoHub.demo.Repositories.TopicRepo;
import com.forohub.ForoHub_API.REST.domain.topics.Topic;
import com.forohub.ForoHub_API.REST.dto.TopicDTO;
import com.forohub.ForoHub_API.REST.errors.CustomConflictException;
import com.forohub.ForoHub_API.REST.infra.security.AuthenticationService;
import com.forohub.ForoHub_API.REST.repository.TopicRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TopicSer {
    @Autowired
    private TopicRepo topicRepository;
    @Autowired
    private ValidationsTopicSer authenticationService;
    @Autowired
    private ValidationsTopicSer validationsTopic;

    public List<TopicDtos> getAllTopics() {
        List<TopicDtos> topics = topicRepository.findAllTopicsWithAuthors();
        return topics.stream()
                .map(topic -> {
                    String authorName = (topic.getAuthor() != null) ? topic.getAuthor() : "Desconocido";
                    String status = getTopicStatus(topic);
                    return new TopicDtos(
                            topic.getId(),
                            topic.getTitle(),
                            topic.getBody(),
                            topic.getCourseName(),
                            authorName,
                            topic.getCreationDate(),
                            status);
                })
                .collect(Collectors.toList());
    }

    public TopicDtos getTopicById(Long id) {
        TopicDtos topic = topicRepository.findById(id).orElse(null);
        if (topic != null) {
            String authorName = (topic.getAuthor() != null) ? topic.getAuthor() : "Desconocido";
            String status = getTopicStatus(topic);
            return new TopicDTO(
                    topic.getId(),
                    topic.getTitle(),
                    topic.getBody(),
                    topic.getCourseName(),
                    authorName,
                    topic.getCreationDate(),
                    status);
        }
        throw new EntityNotFoundException("No se encontró el Topic con id: " + id);
    }

    public TopicDtos createTopic(TopicDtos dto) {
        TopicDtos topic = new Topic();
        topic.setTitle(dto.title());
        topic.setBody(dto.body());
        topic.setCourseName(dto.courseName());
        topic.setCreationDate(LocalDateTime.now());
        var author = authenticationService.getNameAuthenticatedUser();
        topic.setAuthor(author);
        if (validationsTopic.existingValidation(topic.getTitle(), topic.getBody())) {
            throw new ForoExceptions("No puede crear un topic duplicado ");
        }
        return topicRepository.save(topic);
    }

    public TopicDtos updateTopic(Long id, TopicDtos dto) {
        Optional<TopicDtos> existingTopic = topicRepository.findById(id);
        if (existingTopic.isPresent()) {
            TopicDtos topic = existingTopic.get();
            topic.setTitle(dto.title());
            topic.setBody(dto.body());
            topic.setCourseName(dto.courseName());
            var author = authenticationService.getNameAuthenticatedUser();
            topic.setAuthor(author);
            topic.setCreationDate(LocalDateTime.now());
            if (validationsTopic.existingValidation(topic.getTitle(), topic.getBody())){
                throw new ForoExceptions("No puede crear un topic duplicado ");
            }
            return topicRepository.save(topic);
        }
        throw new EntityNotFoundException("No se encontró el Topic con id: " + id);
    }

    public void deleteTopic(Long id) {
        topicRepository.deleteById(id);
    }

    private String getTopicStatus(TopicDtos topic) {
        if (topic.getResponse() != null && !topic.getResponse().isEmpty()) {
            return "Con respuesta";
        } else {
            return "Sin respuesta";
        }
    }

}
