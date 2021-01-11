package ar.com.wolox.wchallenge.service.useralbumservice;

import ar.com.wolox.wchallenge.model.UserAlbum;

import java.util.List;

public interface IUserAlbumService {

    public List<UserAlbum> getAllUserAlbum();

    public List<UserAlbum> saveUserAlbums(List<UserAlbum> userAlbums);

    public void deleteUserAlbums(List<UserAlbum> userAlbums);
}
