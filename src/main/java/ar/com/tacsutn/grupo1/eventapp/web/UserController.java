package ar.com.tacsutn.grupo1.eventapp.web;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
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
     * @param user_id requested user's identifier.
     */
    @GetMapping("/users/{user_id}")
    public MockupResponse getUser(@PathVariable Long user_id) {
        return new MockupResponse(String.format("User %d", user_id));
    }
}