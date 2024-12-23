package com.example.blog.Controller;

import com.example.blog.Api.ApiResponse;
import com.example.blog.Model.User;
import com.example.blog.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")


public class AuthController {

    private final AuthService authService;

    @PostMapping("/regester")
    public ResponseEntity regester(@RequestBody @Valid User user){
        authService.regester(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(" regester success"));
    }



    @GetMapping("/get")
    public ResponseEntity getAllUSer() {
        return ResponseEntity.status(200).body(authService.getAllUser());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @RequestBody @Valid User updatedUser) {
        authService.updateUser(id, updatedUser);
        return ResponseEntity.status(200).body(new ApiResponse("User updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        authService.deleteUser(id);
        return ResponseEntity.status(200).body(new ApiResponse("User deleted successfully"));
    }
}
