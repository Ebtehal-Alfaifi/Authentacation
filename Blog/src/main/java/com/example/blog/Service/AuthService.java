package com.example.blog.Service;

import com.example.blog.Api.ApiException;
import com.example.blog.Model.User;
import com.example.blog.Repository.AuthRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;



    public List<User> getAllUser(){
        return authRepository.findAll();
    }
    public void  regester(User user1){
        user1.setRole("USER");
        String hashpasword=new BCryptPasswordEncoder().encode(user1.getPassword());
        user1.setPassword(hashpasword);
        authRepository.save(user1);
    }

    public void updateUser(Integer id, User updatedUser) {
        User oldUser = authRepository.findUserById(id);
        if(oldUser==null){
            throw new ApiException("User not found");
        }
        oldUser.setUserName(updatedUser.getUsername());
        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
            String hashedPassword = new BCryptPasswordEncoder().encode(updatedUser.getPassword());
            oldUser.setPassword(hashedPassword);
        }
        authRepository.save(oldUser);
    }

    public void deleteUser(Integer id) {
        if (!authRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        authRepository.deleteById(id);
    }

}
