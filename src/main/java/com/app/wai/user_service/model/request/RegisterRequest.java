package com.app.wai.user_service.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * This is class for register payload.
 *   username         varchar(50)  NOT NULL,
 *   email            varchar(50)  NOT NULL,
 *   phone            varchar(20),
 *   fullname         varchar(255) NOT NULL,
 *   password         varchar(255) NOT NULL,
 *   created_at       timestamptz  NOT NULL DEFAULT now(),
 *   updated_at       timestamptz  NOT NULL DEFAULT now(),
 *   last_login_at    timestamptz
 */
@Data
public class RegisterRequest {
    @NotBlank
    @Size(min = 3, max = 20, message = "Username must be between 3–20 characters")
    private String username;
    @NotBlank
    @Size(min = 8, message = "Password min must be atleast 8 characters")
    private String password;
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    @NotBlank(message = "Phone is required")
    @Pattern(regexp = "^[0-9]{10,15}$", message = "Phone must be 10–15 digits")
    private String phone;
    @NotBlank
    @Size(min = 3, max = 20, message = "Username must be between 3–20 characters")
    private String fullname;
}
