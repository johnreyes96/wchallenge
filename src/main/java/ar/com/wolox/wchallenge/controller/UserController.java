package ar.com.wolox.wchallenge.controller;

import ar.com.wolox.wchallenge.dto.UserDTO;
import ar.com.wolox.wchallenge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping(UserController.ROUTE)
public class UserController {

    public static final String ROUTE = "/users";
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public List<UserDTO> list() {
        return getUserServiceInstance().getAllUsers();
    }

    private UserService getUserServiceInstance() {
        if (this.userService == null) {
            this.userService = new UserService(restTemplate);
        }
        return this.userService;
    }
}