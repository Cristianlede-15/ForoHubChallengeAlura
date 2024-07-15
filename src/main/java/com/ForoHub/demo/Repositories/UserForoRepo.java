package com.ForoHub.demo.Repositories;

import com.ForoHub.demo.Domain.Users.UsersForo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserForoRepo extends JpaRepository<UsersForo, Long> {
}
