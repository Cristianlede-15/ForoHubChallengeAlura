package com.ForoHub.demo.Dtos;

import java.time.LocalDateTime;

public record TopicDtos(Long id,
                        String title,
                        String body,
                        String courseName,
                        String author,
                        LocalDateTime creationDate,
                        String estado ) {

}