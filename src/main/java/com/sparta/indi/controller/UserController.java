package com.sparta.indi.controller;

import com.sparta.indi.dto.LoginRequestDto;
import com.sparta.indi.dto.SignupRequestDto;
import com.sparta.indi.entity.User;
import com.sparta.indi.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequestDto signupRequestDto){
        userService.signup(signupRequestDto);
        return "회원 가입 성공";
    }

    @PostMapping("/login-page")
    public String login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response){
        userService.login(loginRequestDto,response);
        return "로그인 성공!";
    }

}