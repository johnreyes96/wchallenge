package ar.com.wolox.wchallenge.service;

import ar.com.wolox.wchallenge.constant.PlaceholderRoute;
import ar.com.wolox.wchallenge.dto.UserDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    private final RestTemplate restTemplate;

    public UserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<UserDTO> getAllUsers() {
        StringBuilder url = new StringBuilder(PlaceholderRoute.ROUTE_SERVICE.getRoute())
                .append(PlaceholderRoute.USERS.getRoute());
        UserDTO[] response = restTemplate.getForObject(url.toString(), UserDTO[].class);
        return Arrays.asList(Objects.requireNonNull(response));
    }
}