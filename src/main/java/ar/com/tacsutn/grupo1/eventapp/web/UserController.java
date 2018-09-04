package ar.com.tacsutn.grupo1.eventapp.web;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@Api(tags = "Users", description = "user resources")
public class UserController {

    /**
     * Create a new user account.
     */
    @PostMapping("/users")
    public MockupResponse createUser(/* @RequestBody User user */) {
        return new MockupResponse("User created");
    }

    /**
     * Returns a single user by identifier.
     * The administrator account is required.
     *
     * @param userId requested user's identifier.
     */
    @GetMapping("/users/{userId}")
    public MockupResponse getUser(@PathVariable Long userId) {
        return new MockupResponse(String.format("User %d", userId));
    }
}