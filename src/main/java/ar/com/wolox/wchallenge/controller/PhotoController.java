package ar.com.wolox.wchallenge.controller;

import ar.com.wolox.wchallenge.dto.AlbumDTO;
import ar.com.wolox.wchallenge.dto.PhotoDTO;
import ar.com.wolox.wchallenge.service.AlbumService;
import ar.com.wolox.wchallenge.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(PhotoController.ROUTE)
public class PhotoController {

    public static final String ROUTE = "/photos";
    public static final String PHOTOS_BY_USER = "/user/{userId}";
    private PhotoService photoService;
    private AlbumService albumService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public List<PhotoDTO> list() {
        return getPhotoServiceInstance().getAllPhotos();
    }

    @GetMapping(PHOTOS_BY_USER)
    public List<PhotoDTO> getPhotosByUserId(@PathVariable short userId) {
        List<AlbumDTO> albumsByUserId = getAlbumsByUserId(userId);
        return getPhotosByUserId(albumsByUserId);
    }

    private List<AlbumDTO> getAlbumsByUserId(short userId) {
        return getAlbumServiceInstance().getAlbumsByUserId(userId);
    }

    private List<PhotoDTO> getPhotosByUserId(List<AlbumDTO> albumDTOListByUserId) {
        List<PhotoDTO> photoDTOSByUserId = new ArrayList<>();
        for (AlbumDTO albumDTO : albumDTOListByUserId) {
            photoDTOSByUserId.addAll(getPhotoServiceInstance().getPhotosByAlbumId(albumDTO.getId()));
        }
        return photoDTOSByUserId;
    }

    private PhotoService getPhotoServiceInstance() {
        if (this.photoService == null) {
            this.photoService = new PhotoService(restTemplate);
        }
        return this.photoService;
    }

    private AlbumService getAlbumServiceInstance() {
        if (this.albumService == null) {
            this.albumService = new AlbumService(restTemplate);
        }
        return this.albumService;
    }
}