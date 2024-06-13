package Ecommerce.controller;

import Ecommerce.dto.UsersInfo;
import Ecommerce.dto.utility.HttpResponse;
import Ecommerce.entity.Users;
import Ecommerce.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@CrossOrigin
public class UsersController {
    @Autowired
    private final UsersService usersService;

    @PostMapping("/register-user")
    public ResponseEntity<HttpResponse<Users>> createUser(@RequestBody Users user) {
        Users newUser = usersService.createUser(user);
        HttpResponse<Users> response = new HttpResponse<>(newUser, "User created successfully", HttpStatus.CREATED);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update-user")
    public ResponseEntity<HttpResponse<Users>> updateUser(@RequestBody UsersInfo usersInfo) {
        Users updatedUser = usersService.updateUser(usersInfo);
        HttpResponse<Users> response = new HttpResponse<>(updatedUser, "User updated successfully", HttpStatus.OK);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<HttpResponse<String>> deleteUser(@RequestParam UUID userId) {
        usersService.deleteUser(userId);
        HttpResponse<String> response = new HttpResponse<>("User deleted successfully", HttpStatus.OK);
        return ResponseEntity.ok(response);
    }
}