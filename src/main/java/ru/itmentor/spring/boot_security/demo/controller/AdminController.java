package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.UserService;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<User> getAllUsers() {
        List<User> users = userService.getUsers();
        return users;
    }

    @PostMapping("/addUser")
    public User createUser(@RequestBody User user) {
        userService.saveUser(user);
        return user;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") long id) {
        User user = userService.getUserById(id);
        return user;
    }

    @PutMapping("/updateUser/{id}")
    public User updateUser(@PathVariable("id") long id, @RequestBody User user) {
        userService.updateUser(id, user);
        return user;
    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.removeUserById(id);
        return "User " + id + " is delete";
    }
}
