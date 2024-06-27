package com.cueshop.controller;

import com.cueshop.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.cueshop.service.EmailService.generateOtp;

@RestController
@CrossOrigin
@RequestMapping("/email")
public class EmailController {
    @Autowired
    EmailService emailService;
    @PostMapping("/send")
    public ResponseEntity<String> sendOTP(@RequestParam String email){
        String otp = generateOtp();
        emailService.storeOtp(email, otp);
        emailService.sendOtpEmail(email, otp);
        return ResponseEntity.ok("Đã gửi mã OTP qua email "+email);
    }
    @PostMapping("/validate")
    public ResponseEntity<String> validateOTP(@RequestBody @RequestParam String email, @RequestParam String otp) {
        String storedOtp = emailService.getOtp(email);
        if (storedOtp != null && storedOtp.equals(otp)) {
            emailService.clearOtp(email);
            return ResponseEntity.ok("Xác thực email thành công!");
        } else {
            return ResponseEntity.badRequest().body("Xác thực email không thành công!");
        }
    }
}
