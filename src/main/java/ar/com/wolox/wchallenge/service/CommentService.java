package ar.com.wolox.wchallenge.service;

import ar.com.wolox.wchallenge.constant.PlaceholderRoute;
import ar.com.wolox.wchallenge.dto.CommentDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class CommentService {

    private final RestTemplate restTemplate;

    public CommentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<CommentDTO> getCommentsByName(String value) {
        StringBuilder url = new StringBuilder(PlaceholderRoute.ROUTE_SERVICE.getRoute())
                .append(PlaceholderRoute.COMMENTS_BY_NAME.getRoute())
                .append(value);
        CommentDTO[] response = restTemplate.getForObject(url.toString(), CommentDTO[].class);
        return Arrays.asList(Objects.requireNonNull(response));
    }
}