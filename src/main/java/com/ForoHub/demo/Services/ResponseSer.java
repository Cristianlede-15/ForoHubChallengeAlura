package com.ForoHub.demo.Services;

import com.ForoHub.demo.Dtos.Responses;
import com.ForoHub.demo.Infraestructure.Security.AuthenticationSer;
import com.ForoHub.demo.Repositories.Response;
import com.ForoHub.demo.Repositories.TopicRepo;
import com.forohub.ForoHub_API.REST.domain.topics.Response;
import com.forohub.ForoHub_API.REST.dto.ResponseDTO;
import com.forohub.ForoHub_API.REST.infra.security.AuthenticationService;
import com.forohub.ForoHub_API.REST.repository.ResponseRepository;
import com.forohub.ForoHub_API.REST.repository.TopicRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResponseSer {
    @Autowired
    private AuthenticationSer authenticationService;
    @Autowired
    private Response responseRepository;
    @Autowired
    private TopicRepo topicRepository;
    public List<Responses> getAllResponses() {
        List<Response> responses = responseRepository.findAll();
        return responses.stream()
                .map(response -> {
                    String author = (response.getResponseAuthor() != null) ? response.getResponseAuthor() : "Desconocido";
                    return new ResponseDTO(
                            response.getResponseTitle(),
                            response.getBody(),
                            author,
                            response.getCreationDate());
                })
                .collect(Collectors.toList());
    }

    public Responses getResponseById(Long id) {
        Response response = responseRepository.findById(id).orElse(null);
        if (response != null){
            return  new Responses(
                    response.getResponseTitle(),
                    response.getBody(),
                    response.getResponseAuthor(),
                    response.getCreationDate());
        }
        return null;
    }

    public Response createResponse(Long id, Responses dto) {
        var author = authenticationService.getNameAuthenticatedUser();
        if (topicRepository.findById(id).isPresent()){
        Response response = new Response();
        response.setTopic(topicRepository.getReferenceById(id));
        response.setResponseAuthor(author);
        response.setResponseTitle(dto.responseTitle());
        response.setBody(dto.body());
        response.setCreationDate(LocalDateTime.now());
        return responseRepository.save(response);
        }
        return new Responses();
    }

        public Response updateResponse (Long id, Responses dto){
            Optional<Response> existingResponse = responseRepository.findById(id);
            if (existingResponse.isPresent()) {
                Response newResponse = existingResponse.get();
                newResponse.setResponseTitle(dto.responseTitle());
                newResponse.setBody(dto.body());
                var author = authenticationService.getNameAuthenticatedUser();
                newResponse.setResponseAuthor(author);
                newResponse.setCreationDate(LocalDateTime.now());
                return responseRepository.save(newResponse);
            }
            throw new EntityNotFoundException("No se encontró el Topic con id: " + id);
        }

        public boolean deleteResponse (Long id){
            if (responseRepository.existsById(id)) {
                responseRepository.deleteById(id);
                return true;
            }
            return false;
        }
    }

