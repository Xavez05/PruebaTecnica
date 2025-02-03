package com.application.security.service;

public interface AuthService {

    String login(String username, String password);
    void register(String username, String password, String role);
}
