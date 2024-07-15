package com.ForoHub.demo.Dtos;

import jakarta.validation.constraints.NotBlank;

public record UserAuthentication(
        @NotBlank
        String login,
        @NotBlank
        String key) {
}