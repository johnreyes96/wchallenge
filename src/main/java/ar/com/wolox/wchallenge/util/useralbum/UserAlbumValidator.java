package ar.com.wolox.wchallenge.util.useralbum;

import ar.com.wolox.wchallenge.controller.UserAlbumController;
import ar.com.wolox.wchallenge.exception.DuplicateRegisterException;
import ar.com.wolox.wchallenge.exception.PermissionNotFoundException;
import ar.com.wolox.wchallenge.model.UserAlbum;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class UserAlbumValidator {

    private UserAlbum userAlbum;

    public UserAlbumValidator(UserAlbum userAlbum) {
        this.userAlbum = userAlbum;
    }

    public void validateIfExistUserAlbum(List<UserAlbum> allUserAlbum) {
        if (existUserAlbum(allUserAlbum)) {
            int userId = this.userAlbum.getUserId();
            int albumId = this.userAlbum.getAlbumId();
            String message = String.format("The user %d already has permissions for album %d", userId, albumId);
            throw new DuplicateRegisterException(message);
        }
    }

    private boolean existUserAlbum(List<UserAlbum> userAlbums) {
        for (UserAlbum userAlbumPersisted : userAlbums) {
            if (userAlbumPersisted.getAlbumId() == this.userAlbum.getAlbumId()
                    && userAlbumPersisted.getUserId() == this.userAlbum.getUserId()) {
                return true;
            }
        }
        return false;
    }

    public void validatePermission() {
        updatePermissionToUpperCase();
        validateIfExistPermission();
    }

    private void updatePermissionToUpperCase() {
        this.userAlbum.setPermission(this.userAlbum.getPermission().toUpperCase());
    }

    public void validateIfExistPermission() {
        if (!existPermission()) {
            String message = String.format("Not exist the permission %s", this.userAlbum.getPermission());
            throw new PermissionNotFoundException(message);
        }
    }

    private boolean existPermission() {
        List<String> permissionsAllowed = UserAlbumUtil.getPermissionsAllowed();
        if (permissionsAllowed.contains(this.userAlbum.getPermission())) {
            return true;
        }
        return false;
    }

    public UserAlbum getUserAlbum() {
        return userAlbum;
    }
}
