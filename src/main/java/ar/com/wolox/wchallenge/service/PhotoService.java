package ar.com.wolox.wchallenge.service;

import ar.com.wolox.wchallenge.constant.PlaceholderRoute;
import ar.com.wolox.wchallenge.dto.PhotoDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class PhotoService {

    private final RestTemplate restTemplate;

    public PhotoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<PhotoDTO> getAllPhotos() {
        StringBuilder url = new StringBuilder(PlaceholderRoute.ROUTE_SERVICE.getRoute())
                .append(PlaceholderRoute.PHOTOS.getRoute());
        PhotoDTO[] response = restTemplate.getForObject(url.toString(), PhotoDTO[].class);
        return Arrays.asList(Objects.requireNonNull(response));
    }

    public List<PhotoDTO> getPhotosByAlbumId(int albumId) {
        StringBuilder url = new StringBuilder(PlaceholderRoute.ROUTE_SERVICE.getRoute())
                .append(PlaceholderRoute.PHOTOS_BY_ALBUM_ID.getRoute())
                .append(albumId);
        PhotoDTO[] response = restTemplate.getForObject(url.toString(), PhotoDTO[].class);
        return Arrays.asList(Objects.requireNonNull(response));
    }
}