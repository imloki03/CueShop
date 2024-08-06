package com.cueshop.service;

import com.cueshop.DTO.LoginDTO;
import com.cueshop.DTO.UserDTO;
import com.cueshop.model.User;
import com.cueshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.cueshop.service.EmailService.generateOtp;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    EmailService emailService;
    @Transactional
    public void register(User user){
        User existedUser = userRepository.findByUsername(user.getUsername()).orElse(null);
        if (existedUser==null){
            userRepository.save(user);
        }
        else{
            throw new RuntimeException("Tên người dùng đã tồn tại!");
        }
    }
    public void login(LoginDTO loginDTO){
        User user = userRepository.findByUsername(loginDTO.getUsername()).orElse(null);
        if (user==null){
            throw new RuntimeException("Không tìm thấy người dùng!");
        } else {
            if (!user.getPassword().equals(loginDTO.getPassword())){
                throw new RuntimeException("Mật khẩu chưa chính xác!");
            } else {
                if (user.getRole().equals("admin")){
                    throw new RuntimeException("admin");
                }
            }
        }
    }
    public UserDTO getUserInfo(String username){
        User user = userRepository.findByUsername(username).orElse(null);
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username);
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone(user.getPhone());
        return userDTO;
    }

    public String forgotPassword(String username){
        String email = getUserInfo(username).getEmail();
        String otp = generateOtp();
        emailService.storeOtp(email, otp);
        emailService.sendOtpEmail(email, otp);
        return email;
    }

    @Transactional
    public void resetPassword(String username, String password){
        User user = userRepository.findByUsername(username).orElseThrow();
        user.setPassword(password);
        userRepository.save(user);
    }
}
