package com.sparta.indi.service;

import com.sparta.indi.dto.LoginRequestDto;
import com.sparta.indi.dto.SignupRequestDto;
import com.sparta.indi.entity.User;
import com.sparta.indi.entity.UserRoleEnum;
import com.sparta.indi.jwt.JwtUtil;
import com.sparta.indi.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private static final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    private final JwtUtil jwtUtil;

    @Transactional
    public void signup(SignupRequestDto signupRequestDto) {
        String username = signupRequestDto.getUsername();
        String pw = signupRequestDto.getPw();
        String email = signupRequestDto.getEmail();
        long ph_number = signupRequestDto.getPh_number();

        Optional<User> found_username = userRepository.findByUsername(username);
        if (found_username.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }
        Optional<User> found_email = userRepository.findByUsername(email);
        if (found_email.isPresent()) {
            throw new IllegalArgumentException("이미 등록된 이메일입니다.");
        }
        UserRoleEnum role = UserRoleEnum.USER;
        if (signupRequestDto.isAdmin()) {
            if (!signupRequestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }
        User user = new User(username, pw, email, ph_number, role);
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public void login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPw();

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        if (!user.getPw().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER,
                jwtUtil.createToken(user.getUsername(), user.getRole()));
    }

}
