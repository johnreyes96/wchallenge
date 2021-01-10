package ar.com.wolox.wchallenge.controller;

import ar.com.wolox.wchallenge.constant.PermissionEnum;
import ar.com.wolox.wchallenge.exception.DuplicateRegisterException;
import ar.com.wolox.wchallenge.exception.PermissionNotFoundException;
import ar.com.wolox.wchallenge.model.UserAlbum;
import ar.com.wolox.wchallenge.service.useralbumservice.IUserAlbumService;
import ar.com.wolox.wchallenge.util.useralbum.UserAlbumUtil;
import ar.com.wolox.wchallenge.util.useralbum.UserAlbumValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(value=UserAlbumController.ROUTE)
public class UserAlbumController {

    public static final String ROUTE = "share_album";

    @Autowired
    private IUserAlbumService userAlbumService;

    @PostMapping
    public List<UserAlbum> createUserAlbum(@RequestBody UserAlbum userAlbum) {
        userAlbum = beforeCreateUserAlbum(userAlbum);
        List<UserAlbum> userAlbums = UserAlbumUtil.createUserAlbums(userAlbum);
        return userAlbumService.saveUserAlbums(userAlbums);
    }

    private UserAlbum beforeCreateUserAlbum(UserAlbum userAlbum) {
        UserAlbumValidator userAlbumValidator = new UserAlbumValidator(userAlbum);
        List<UserAlbum> allUserAlbum = getAllUserAlbum();
        userAlbumValidator.validateIfExistUserAlbum(allUserAlbum);
        userAlbumValidator.validatePermission();
        return userAlbumValidator.getUserAlbum();
    }

    public List<UserAlbum> getAllUserAlbum() {
        return userAlbumService.getAllUserAlbum();
    }
}