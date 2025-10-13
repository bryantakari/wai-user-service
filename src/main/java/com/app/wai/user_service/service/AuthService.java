package com.app.wai.user_service.service;


import com.app.wai.user_service.common.response.BaseValueResponse;
import com.app.wai.user_service.model.dto.LoginDto;
import com.app.wai.user_service.model.dto.RegisterDto;
import com.app.wai.user_service.model.request.LoginRequest;
import com.app.wai.user_service.model.request.RegisterRequest;

/**
 * Auth Service Interface.
 */
public interface AuthService {

    BaseValueResponse<RegisterDto> registerAccount(RegisterRequest request);

    BaseValueResponse<LoginDto> loginAccount(LoginRequest request);

    BaseValueResponse<Boolean> validateToken();

    BaseValueResponse<LoginDto> refreshToken(String token);

}
