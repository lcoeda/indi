package com.sparta.indi.controller;

import com.sparta.indi.dto.LoginRequestDto;
import com.sparta.indi.dto.SecurityExceptionDto;
import com.sparta.indi.dto.SignupRequestDto;
import com.sparta.indi.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<SecurityExceptionDto> signup(@RequestBody SignupRequestDto signupRequestDto){
        userService.signup(signupRequestDto);
        String text = "회원가입 성공";
        return ResponseEntity.ok(new SecurityExceptionDto(200, text));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response){
        userService.login(loginRequestDto,response);
        String text = "로그인 성공";
        return ResponseEntity.ok(new SecurityExceptionDto(200, text));
    }

}