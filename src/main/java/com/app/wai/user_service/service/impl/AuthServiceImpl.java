package com.app.wai.user_service.service.impl;

import com.app.wai.user_service.common.JwtHelper;
import com.app.wai.user_service.common.RequestUtils;
import com.app.wai.user_service.common.exception.ApplicationErrorCode;
import com.app.wai.user_service.common.exception.ServiceException;
import com.app.wai.user_service.common.response.BaseValueResponse;
import com.app.wai.user_service.mapper.UserMapper;
import com.app.wai.user_service.model.dto.JwtTokenDto;
import com.app.wai.user_service.model.dto.LoginDto;
import com.app.wai.user_service.model.dto.RegisterDto;
import com.app.wai.user_service.model.entity.Account;
import com.app.wai.user_service.model.request.LoginRequest;
import com.app.wai.user_service.model.request.RegisterRequest;
import com.app.wai.user_service.service.AuthService;
import com.app.wai.user_service.service.UserService;
import io.jsonwebtoken.Claims;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final UserMapper userMapper;
    private final JwtHelper jwtHelper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public BaseValueResponse<RegisterDto> registerAccount(RegisterRequest request) {
        Account account = userMapper.mappingRegisterToAccount(request);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return BaseValueResponse.<RegisterDto>builder()
            .data(userMapper.mappingAccountToDto(userService.insertAccount(account))).build();
    }

    @Override
    public BaseValueResponse<LoginDto> loginAccount(LoginRequest request) {
        Account user = userService.getAccountByUsername(request.getUsername())
            .orElseThrow(() -> new ServiceException("Invalid username or password",
                ApplicationErrorCode.BAD_REQUEST));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new ServiceException("Invalid username or password",
                ApplicationErrorCode.BAD_REQUEST);
        }

        return BaseValueResponse.<LoginDto>builder()
            .data(generateLoginTokens(user))
            .build();
    }

    @Override
    public BaseValueResponse<Boolean> validateToken() {
        String authHeader = RequestUtils.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new ServiceException("Missing Authorization header",
                ApplicationErrorCode.UNAUTHORIZED);
        }

        String token = authHeader.replace("Bearer ", "").trim();

        jwtHelper.validateJwtToken(token).orElseThrow(
            () -> new ServiceException("Token not valid", ApplicationErrorCode.UNAUTHORIZED));

        return BaseValueResponse.<Boolean>builder().data(true).build();
    }

    @Override
    public BaseValueResponse<LoginDto> refreshToken(String token) {
        Optional<Claims> claimsOpt = jwtHelper.validateJwtToken(token);
        if (claimsOpt.isEmpty()) {
            throw new ServiceException("Refresh Token not valid",
                ApplicationErrorCode.UNAUTHORIZED);
        }
        Claims userClaims = claimsOpt.get();
        var user = userService.getAccountByUsername(userClaims.getSubject()).orElseThrow(
            () -> new ServiceException("Invalid User", ApplicationErrorCode.BAD_REQUEST));


        return BaseValueResponse.<LoginDto>builder().data(generateLoginTokens(user)).build();
    }

    private LoginDto generateLoginTokens(Account user) {
        JwtTokenDto jwtTokenDto = userMapper.mappingAccountToJwtDto(user);

        LoginDto loginDto = new LoginDto();
        loginDto.setToken(jwtHelper.generateToken(jwtTokenDto));
        loginDto.setRefreshToken(jwtHelper.generateRefreshToken(jwtTokenDto));
        return loginDto;
    }
}
