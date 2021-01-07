package ar.com.wolox.wchallenge.controller;

import ar.com.wolox.wchallenge.dto.PhotoDTO;
import ar.com.wolox.wchallenge.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping(PhotoController.ROUTE)
public class PhotoController {

    public static final String ROUTE = "/photos";
    private PhotoService photoService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public List<PhotoDTO> list() {
        return getPhotoServiceInstance().getAllPhotos();
    }

    private PhotoService getPhotoServiceInstance() {
        if (this.photoService == null) {
            this.photoService = new PhotoService(restTemplate);
        }
        return this.photoService;
    }
}