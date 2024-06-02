package com.cueshop.service;

import com.cueshop.DTO.LoginDTO;
import com.cueshop.model.User;
import com.cueshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Transactional
    public void register(User user){
        User existedUser = userRepository.findByUsername(user.getUsername());
        if (existedUser==null){
            userRepository.save(user);
        }
        else{
            throw new RuntimeException("Tên người dùng đã tồn tại!");
        }
    }
    public void login(LoginDTO loginDTO){
        User user = userRepository.findByUsername(loginDTO.getUsername());
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
}
