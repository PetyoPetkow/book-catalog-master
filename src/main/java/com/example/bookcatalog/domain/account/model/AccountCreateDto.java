package com.example.bookcatalog.domain.account.model;
import com.example.bookcatalog.infrastructure.validator.password.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AccountCreateDto(
        @NotBlank(message = "Email is mandatory")
        @Email(message = "Email should be valid")
        String email,

        @Size(min = 3, max = 16, message = "Username should be between 3 and 16 characters long")
        @NotBlank(message = "Username is mandatory")
        String username,

        @NotBlank(message = "Password is mandatory")
        @ValidPassword
        String password
) {
}
