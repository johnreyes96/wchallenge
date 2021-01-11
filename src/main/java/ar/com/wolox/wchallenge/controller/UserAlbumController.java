package ar.com.wolox.wchallenge.controller;

import ar.com.wolox.wchallenge.dto.UserPermissionToAlbumDTO;
import ar.com.wolox.wchallenge.model.UserAlbum;
import ar.com.wolox.wchallenge.service.useralbumservice.IUserAlbumService;
import ar.com.wolox.wchallenge.util.useralbum.UserAlbumUtil;
import ar.com.wolox.wchallenge.util.useralbum.UserAlbumValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value=UserAlbumController.ROUTE)
public class UserAlbumController {

    public static final String ROUTE = "share_album";
    public static final String PERMISSION_ALBUM_ID = "/{permission}/{albumId}";

    @Autowired
    private IUserAlbumService userAlbumService;

    @PostMapping
    public List<UserAlbum> createUserAlbum(@RequestBody UserAlbum userAlbum) {
        userAlbum = beforeCreateUserAlbum(userAlbum);
        List<UserAlbum> userAlbums = UserAlbumUtil.createUserAlbums(userAlbum);
        return userAlbumService.saveUserAlbums(userAlbums);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public List<UserAlbum> updateUserAlbum(@RequestBody UserAlbum userAlbum) {
        userAlbum = beforeUpdateUserAlbum(userAlbum);
        List<UserAlbum> userAlbums = UserAlbumUtil.createUserAlbums(userAlbum);
        return userAlbumService.saveUserAlbums(userAlbums);
    }

    @GetMapping(PERMISSION_ALBUM_ID)
    public List<UserPermissionToAlbumDTO> getUsersPermissionToAlbumIdAndPermission(@PathVariable int albumId,
                                                                                   @PathVariable String permission) {
        return getUserPermissionToAlbumDTOByAlbumIdAndPermission(albumId, permission);
    }

    public List<UserAlbum> getAllUserAlbum() {
        return userAlbumService.getAllUserAlbum();
    }

    private void deleteUserAlbumsByUserIdAndAlbumId(List<UserAlbum> userAlbumsToDelete) {
        userAlbumService.deleteUserAlbums(userAlbumsToDelete);
    }

    private UserAlbum beforeCreateUserAlbum(UserAlbum userAlbum) {
        UserAlbumValidator userAlbumValidator = new UserAlbumValidator(userAlbum);
        List<UserAlbum> allUserAlbum = getAllUserAlbum();
        userAlbumValidator.validateUserAlbumPersistedToCreate(allUserAlbum);
        userAlbumValidator.validatePermission();
        return userAlbumValidator.getUserAlbum();
    }

    private UserAlbum beforeUpdateUserAlbum(UserAlbum userAlbum) {
        UserAlbumValidator userAlbumValidator = new UserAlbumValidator(userAlbum);
        List<UserAlbum> allUserAlbum = getAllUserAlbum();
        userAlbumValidator.validateUserAlbumPersistedToUpdate(allUserAlbum);
        userAlbumValidator.validatePermission();
        List<UserAlbum> userAlbumsToDelete = userAlbumValidator.getUserAlbumsPersistedByUserIdAndAlbumId(allUserAlbum);
        deleteUserAlbumsByUserIdAndAlbumId(userAlbumsToDelete);
        return userAlbumValidator.getUserAlbum();
    }

    private List<UserPermissionToAlbumDTO> getUserPermissionToAlbumDTOByAlbumIdAndPermission(int albumId,
                                                                                             String permission) {
        UserAlbum userAlbum = UserAlbumUtil.createUserAlbumWithOnlyPermission(permission);
        UserAlbumValidator userAlbumValidator = new UserAlbumValidator(userAlbum);
        userAlbumValidator.validatePermission();
        List<UserAlbum> allUserAlbum = getAllUserAlbum();
        return userAlbumValidator.getUserPermissionToAlbumDTOByAlbumIdAndPermission(allUserAlbum, albumId,
                userAlbum.getPermission());
    }
}