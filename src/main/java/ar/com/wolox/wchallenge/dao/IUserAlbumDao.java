package ar.com.wolox.wchallenge.dao;

import ar.com.wolox.wchallenge.model.UserAlbum;
import org.springframework.data.repository.CrudRepository;

public interface IUserAlbumDao extends CrudRepository<UserAlbum, Long> {
}