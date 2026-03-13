package ua.mai.zine.redis.rest;

import ua.mai.zine.redis.dto.UserDto;
import ua.mai.zine.redis.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/redis/v1/users")
@RequiredArgsConstructor
public class UserRestControllerV1 {
    private final UserService userService;

    @PostMapping
    public UserDto create(@RequestBody UserDto user) {
        return userService.create(user);
    }

    @GetMapping("/{id}")
    public UserDto get(@PathVariable String id) {
        return userService.get(id);
    }

    @PutMapping("/{id}")
    public UserDto update(@PathVariable String id, @RequestBody UserDto dto) {
        return userService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        userService.delete(id);
    }
}
