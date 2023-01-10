package com.sparta.indi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    private String username;
    private String pw;
    private String email;
    private long ph_number;
    private boolean admin = false;
    private String adminToken = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";
}