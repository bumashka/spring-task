package edu.hsai.movierecommendation.entity.user.controller;

import edu.hsai.movierecommendation.entity.user.abstraction.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public record UserController(UserService userService) {
    @GetMapping("/getUser/{id}")
    public UserService.UserDto findById(@PathVariable Long id) {
        return userService.getById(id);
    }
}
