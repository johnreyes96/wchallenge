package ar.com.wolox.wchallenge.controller;

import ar.com.wolox.wchallenge.dto.CommentDTO;
import ar.com.wolox.wchallenge.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping(CommentController.ROUTE)
public class CommentController {

    public static final String ROUTE = "/comments";
    public static final String COMMENTS_BY_NAME = "/name/{name}";
    private CommentService commentService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(COMMENTS_BY_NAME)
    public List<CommentDTO> getCommentsByName(@PathVariable String name) {
        return getCommentServiceInstance().getCommentsByName(name);
    }

    private CommentService getCommentServiceInstance() {
        if (this.commentService == null) {
            this.commentService = new CommentService(restTemplate);
        }
        return this.commentService;
    }
}