package com.hoaxify.web.service.user;

import com.hoaxify.web.service.user.validation.UniqueEmail;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Users", uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @NotBlank (message = "{hoaxify.constraint.username.notblank}")
    @Size(min = 4, max = 20)
    String username;

    @NotBlank (message = "{hoaxify.constraint.email.notblank}")
    @Email
     @UniqueEmail (message = "{hoaxify.constraint.email.notunique}")
    String email;

    @NotBlank (message = "{hoaxify.constraint.password.notblank}")
    @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+",message = "{hoaxify.constraint.password.pattern}")
    String password;


}
