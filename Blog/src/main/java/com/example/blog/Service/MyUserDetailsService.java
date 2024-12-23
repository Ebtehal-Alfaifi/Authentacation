package com.example.blog.Service;

import com.example.blog.Api.ApiException;
import com.example.blog.Model.User;
import com.example.blog.Repository.AuthRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor


public class MyUserDetailsService implements UserDetailsService {

    private final AuthRepository authRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=authRepository.findUserByUserName(username);
        if (user==null){
            throw new ApiException(" wrong user Name or password");
        }
        return user;
    }

}
