package com.example.blog.Repository;

import com.example.blog.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<User,Integer> {
    User findUserByUserName(String userName);
    User findUserById(Integer id);
}
