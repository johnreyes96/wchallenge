package ar.com.wolox.wchallenge.service;

import ar.com.wolox.wchallenge.constant.PlaceholderRoute;
import ar.com.wolox.wchallenge.dto.AlbumDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class AlbumService {

    private final RestTemplate restTemplate;

    public AlbumService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<AlbumDTO> getAllAlbums() {
        StringBuilder url = new StringBuilder(PlaceholderRoute.ROUTE_SERVICE.getRoute())
                .append(PlaceholderRoute.ALBUMS.getRoute());
        AlbumDTO[] response = restTemplate.getForObject(url.toString(), AlbumDTO[].class);
        return Arrays.asList(Objects.requireNonNull(response));
    }

    public List<AlbumDTO> getAlbumsByUserId(short userId) {
        StringBuilder url = new StringBuilder(PlaceholderRoute.ROUTE_SERVICE.getRoute())
                .append(PlaceholderRoute.ALBUMS_BY_USER_ID.getRoute())
                .append(userId);
        AlbumDTO[] response = restTemplate.getForObject(url.toString(), AlbumDTO[].class);
        return Arrays.asList(Objects.requireNonNull(response));
    }
}