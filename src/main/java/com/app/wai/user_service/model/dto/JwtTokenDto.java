package com.app.wai.user_service.model.dto;

import lombok.Data;

/**
 * Jwt Token Dto class.
 */
@Data
public class JwtTokenDto {
    private Integer userId;
    private String username;
    private String fullname;
}
