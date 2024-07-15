package com.ForoHub.demo.Repositories;

import com.ForoHub.demo.Domain.Users.UsersApi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserApiRepo extends JpaRepository<UsersApi, Long> {
}
