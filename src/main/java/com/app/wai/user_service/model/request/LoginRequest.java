package com.app.wai.user_service.model.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * This is class for register payload.
 *   username         varchar(50)  NOT NULL,
 *   password         varchar(255) NOT NULL
 */
@Data
public class LoginRequest {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
}
