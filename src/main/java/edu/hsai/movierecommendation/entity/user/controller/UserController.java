package edu.hsai.movierecommendation.entity.user.controller;

import edu.hsai.movierecommendation.entity.user.abstraction.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
public record UserController(UserService userService) {
    @GetMapping("/user/id/{id}")
    public UserService.UserDto findById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @GetMapping("/user/nickname/{nickname}")
    public UserService.UserDto findByNickname(@PathVariable String nickname) {
        return userService.getByNickname(nickname);
    }

    @PostMapping("/signUp")
    public Long singUp(@RequestBody UserService.AddUserDto addUserDto) {return userService.addUser(addUserDto);}
}
