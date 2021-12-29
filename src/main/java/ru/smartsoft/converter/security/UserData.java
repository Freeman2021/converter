package ru.smartsoft.converter.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserData {
    public static final String LOGIN = "admin";
    public static final String PASSWORD = "123";

    private String login;
    private String password;

}