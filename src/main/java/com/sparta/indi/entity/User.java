package com.sparta.indi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Getter
@Entity(name = "users")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    @Pattern(regexp = "(?=.+[0-9])(?=.+[a-z]).{4,10}",message = "id는 4~10자 이내에 소문자, 숫자를 하나 이상 포함해야합니다.")
    private String username;

    @Column(nullable = false)
    @Size(min = 8, max = 15, message = "password는 8~15글자로 만들어야합니다.")
    @Pattern(regexp = "(?=.+[0-9])(?=.+[a-z])(?=.+[A-Z]).{8,15}",message = "pw는 8~15자 이내에 소문자, 대문자, 숫자를 하나 이상 포함해야합니다.")
    private String pw;

    @Column(nullable = false, unique = true)
    @Email
    private String email;

    private long ph_number;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    public User(String username, String pw, String email,long ph_number, UserRoleEnum role){
        this.username = username;
        this.pw = pw;
        this.email = email;
        this.ph_number = ph_number;
        this.role = role;
    }

}
