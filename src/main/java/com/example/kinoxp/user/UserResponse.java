package com.example.kinoxp.user;

import java.util.List;

public record UserResponse(String username, List<String> roles) {}
