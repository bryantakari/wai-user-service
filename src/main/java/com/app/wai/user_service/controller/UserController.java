package com.app.wai.user_service.controller;

import com.app.wai.user_service.common.response.BaseValueResponse;
import com.app.wai.user_service.model.dto.LoginDto;
import com.app.wai.user_service.model.dto.RegisterDto;
import com.app.wai.user_service.model.request.LoginRequest;
import com.app.wai.user_service.model.request.RegisterRequest;
import com.app.wai.user_service.service.AuthService;
import io.opentelemetry.api.trace.Span;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/user")
@AllArgsConstructor
public class UserController {
    private static final org.slf4j.Logger log =
        org.slf4j.LoggerFactory.getLogger(UserController.class);
    private final AuthService authService;

    @GetMapping("/test")
    public ResponseEntity<String> test() {
//        var ctx = io.opentelemetry.api.trace.Span.current().getSpanContext();
//        log.info("Hello — otel traceId={} spanId={}", ctx.getTraceId(), ctx.getSpanId());
        Span.current().setAttribute("test.endpoint", true);
        return new ResponseEntity<>("Good Job", HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<BaseValueResponse<RegisterDto>> registerAccount(@RequestBody
    RegisterRequest request) {
        return new ResponseEntity<>(authService.registerAccount(request), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<BaseValueResponse<LoginDto>> loginAccount(@RequestBody
    LoginRequest request) {
        return new ResponseEntity<>(authService.loginAccount(request), HttpStatus.OK);
    }

    @GetMapping("/validate-token")
    public ResponseEntity<BaseValueResponse<Boolean>> validateToken() {

        return new ResponseEntity<>(authService.validateToken(), HttpStatus.OK);
    }

    @GetMapping("/refresh-token")
    public ResponseEntity<BaseValueResponse<LoginDto>> loginAccount(
        @RequestParam String refreshToken) {
        return new ResponseEntity<>(authService.refreshToken(refreshToken), HttpStatus.OK);
    }
}
