package ar.com.wolox.wchallenge.controller;

import ar.com.wolox.wchallenge.dto.AlbumDTO;
import ar.com.wolox.wchallenge.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping(AlbumController.ROUTE)
public class AlbumController {

    public static final String ROUTE = "/albums";
    private AlbumService albumService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public List<AlbumDTO> list() {
        return getAlbumServiceInstance().getAllAlbums();
    }

    private AlbumService getAlbumServiceInstance() {
        if (this.albumService == null) {
            this.albumService = new AlbumService(restTemplate);
        }
        return this.albumService;
    }
}