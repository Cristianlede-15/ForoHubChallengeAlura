package com.ForoHub.demo.Domain.Users;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "user_foro")
@Table(name = "users_foro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class UsersForo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String userName;
    private String password;


}
