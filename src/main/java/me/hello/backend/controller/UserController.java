package me.hello.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.hello.backend.model.User;
import me.hello.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:8080")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    @Operation(summary = "새 사용자 생성", description = "제공된 정보로 새 사용자를 생성합니다.")
    public User createUser(@RequestBody User user) {
        userService.createUser(user);
        return user;
    }

    @GetMapping("/{id}")
    @Operation(summary = "ID로 사용자 조회", description = "고유 ID로 사용자를 조회합니다.")
    public User getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @GetMapping
    @Operation(summary = "모든 사용자 조회", description = "모든 사용자의 목록을 조회합니다.")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/{id}")
    @Operation(summary = "사용자 정보 업데이트", description = "고유 ID로 기존 사용자의 정보를 업데이트합니다.")
    public User updateUser(@PathVariable String id, @RequestBody User user) {
        userService.updateUser(id, user);
        return user;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "사용자 삭제", description = "고유 ID로 사용자를 삭제합니다.")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }
}