package com.ForoHub.demo.Dtos;

import java.time.LocalDateTime;

public record Responses(
        String responseTitle,
        String body,
        String author,
        LocalDateTime creationDate
) {


}
