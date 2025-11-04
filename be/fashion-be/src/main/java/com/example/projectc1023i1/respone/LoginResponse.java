package com.example.projectc1023i1.respone;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * LoginResponse - Response DTO cho login endpoint
 * Chứa JWT token và thông tin user (user_principal)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {
    private String token;
    private UserRespone user;
}

