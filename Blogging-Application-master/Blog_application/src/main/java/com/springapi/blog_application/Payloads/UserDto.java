package com.springapi.blog_application.Payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {
    private int id;

    @NotEmpty
    @Size(min = 2, max = 20, message = "Enter valid User name")
    private String name;

    @Email(message = "Your Email is not valid")
    private String email;

    @NotEmpty()
    @Size(min = 7 , max = 12, message = "enter valid password")

    private String password;

    @NotEmpty
    private String about;
}
