package com.ForoHub.demo.Dtos;

public record ForoUser(
        Long id,
        String email,
        String userName,
        String password
) {
}
