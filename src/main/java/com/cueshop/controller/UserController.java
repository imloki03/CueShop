package com.cueshop.controller;

import com.cueshop.DTO.LoginDTO;
import com.cueshop.model.User;
import com.cueshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user){
        try {
            userService.register(user);
            return ResponseEntity.ok("Đăng ký tài khoản mới thành công!");
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO){
        try {
            userService.login(loginDTO);
            return ResponseEntity.ok("Đăng nhập thành công!");
        } catch (RuntimeException e) {
            if (e.getMessage().equals("admin")){
                return ResponseEntity.accepted().body("Đã đăng nhập vào trang quản lý!");
            }
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
