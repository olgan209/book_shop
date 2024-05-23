package practice.ex.book_shop.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import practice.ex.book_shop.dto.user.UserRequest;
import practice.ex.book_shop.dto.user.UserResponse;
import practice.ex.book_shop.service.UserService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public UserResponse getById(@PathVariable Integer id, @RequestHeader("Authorization") String token){
        return userService.getById(id, token);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id){
        userService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public void updateUser(@PathVariable Integer id, @RequestBody UserRequest userRequest){
        userService.updateById(id, userRequest);
    }
    @GetMapping("/getAll")
    public List<UserResponse> users() {
        return userService.getAll();
    }


}