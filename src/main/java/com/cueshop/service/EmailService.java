package com.cueshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;
    private Map<String, String> otpStorage = new HashMap<>();

    public void storeOtp(String email, String otp) {
        otpStorage.put(email, otp);
    }

    public String getOtp(String email) {
        return otpStorage.get(email);
    }

    public void clearOtp(String email) {
        otpStorage.remove(email);
    }
    public void sendOtpEmail(String toEmail, String otpCode) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("OTP Verification");
        message.setText("Your OTP code is: " + otpCode);

        mailSender.send(message);
    }
    public static String generateOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }
}