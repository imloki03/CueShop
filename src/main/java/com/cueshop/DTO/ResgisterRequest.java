package com.cueshop.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResgisterRequest {
    String username;
    String password;
    String name;
    String email;
    String phone;
}
