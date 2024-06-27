package com.cueshop.controller;

import com.cueshop.DTO.LoginDTO;
import com.cueshop.DTO.UserDTO;
import com.cueshop.DTO.UsernameDTO;
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
            user.setRole("user");
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
    @PostMapping("/get_user_info")
    public ResponseEntity<UserDTO> getUserInfo(@RequestBody UsernameDTO username) {
        return ResponseEntity.ok(userService.getUserInfo(username.getUsername()));
    }
    @PostMapping("/forgot_password")
    public ResponseEntity<String> forgotPassword(@RequestParam String username){
        try {
            String email = userService.forgotPassword(username);
            System.out.println(email);
            return ResponseEntity.ok(email);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/reset_password")
    public ResponseEntity<String> resetPassword(@RequestParam String username, @RequestParam String password) {
        try {
            userService.resetPassword(username, password);
            return ResponseEntity.ok("Thay đổi mật khẩu mới thành công!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
