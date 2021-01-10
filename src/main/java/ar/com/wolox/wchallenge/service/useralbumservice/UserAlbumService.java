package ar.com.wolox.wchallenge.service.useralbumservice;

import ar.com.wolox.wchallenge.dao.IUserAlbumDao;
import ar.com.wolox.wchallenge.model.UserAlbum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAlbumService implements IUserAlbumService {

    @Autowired
    private IUserAlbumDao userAlbumDao;

    @Override
    public List<UserAlbum> getAllUserAlbum() {
        return (List<UserAlbum>) userAlbumDao.findAll();
    }

    @Override
    public List<UserAlbum> saveUserAlbums(List<UserAlbum> userAlbums) {
        return (List<UserAlbum>) userAlbumDao.saveAll(userAlbums);
    }
}