package com.cueshop.controller;

import com.cueshop.DTO.*;
import com.cueshop.model.Role;
import com.cueshop.model.User;
import com.cueshop.service.AuthenticationService;
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
    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody ResgisterRequest request){
        try {
            return ResponseEntity.ok(authenticationService.register(request));
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request){

        try {
            return ResponseEntity.ok(authenticationService.login(request));
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body("Tên đăng nhập hoặc mật khẩu chưa chính xác!");
        }
    }

    //    @PostMapping("/register")
//    public ResponseEntity<String> register(@RequestBody User user){
//        try {
//            user.setRole(Role.user);
//            userService.register(user);
//            return ResponseEntity.ok("Đăng ký tài khoản mới thành công!");
//        } catch (RuntimeException e){
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO){
//        try {
//            userService.login(loginDTO);
//            return ResponseEntity.ok("Đăng nhập thành công!");
//        } catch (RuntimeException e) {
//            if (e.getMessage().equals("admin")){
//                return ResponseEntity.accepted().body("Đã đăng nhập vào trang quản lý!");
//            }
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
    @PostMapping("/get_user_info")
    public ResponseEntity<?> getUserInfo(@RequestBody UsernameDTO username) {
        try {
            System.out.println("call");
            return ResponseEntity.ok(userService.getUserInfo(username.getUsername()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("bug"+e.getMessage());
        }
    }
    @PostMapping("/forgot_password")
    public ResponseEntity<String> forgotPassword(@RequestParam String username){
        try {
            String email = userService.forgotPassword(username);
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
