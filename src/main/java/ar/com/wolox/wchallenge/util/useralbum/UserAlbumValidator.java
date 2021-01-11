package ar.com.wolox.wchallenge.util.useralbum;

import ar.com.wolox.wchallenge.exception.DuplicateRegisterException;
import ar.com.wolox.wchallenge.exception.PermissionNotFoundException;
import ar.com.wolox.wchallenge.model.UserAlbum;

import java.util.ArrayList;
import java.util.List;

public class UserAlbumValidator {

    private UserAlbum userAlbum;

    public UserAlbumValidator(UserAlbum userAlbum) {
        this.userAlbum = userAlbum;
    }

    public void validateUserAlbumPersistedToCreate(List<UserAlbum> allUserAlbum) {
        if (existUserAlbum(allUserAlbum)) {
            int userId = this.userAlbum.getUserId();
            int albumId = this.userAlbum.getAlbumId();
            String message = String.format("The user %d already has permissions for album %d", userId, albumId);
            throw new DuplicateRegisterException(message);
        }
    }

    public void validateUserAlbumPersistedToUpdate(List<UserAlbum> allUserAlbum) {
        if (!existUserAlbum(allUserAlbum)) {
            int userId = this.userAlbum.getUserId();
            int albumId = this.userAlbum.getAlbumId();
            String message = String.format("The user %d does not have permissions for album %d", userId, albumId);
            throw new PermissionNotFoundException(message);
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

    public List<UserAlbum> getUserAlbumsPersistedByUserIdAndAlbumId(List<UserAlbum> allUserAlbum) {
        List<UserAlbum> userAlbumsPersistedByUserIdAndAlbumId = new ArrayList<>();
        for (UserAlbum userAlbumPersisted : allUserAlbum) {
            if (userAlbumPersisted.getAlbumId() == this.userAlbum.getAlbumId()
                    && userAlbumPersisted.getUserId() == this.userAlbum.getUserId()) {
                userAlbumsPersistedByUserIdAndAlbumId.add(userAlbumPersisted);
                if (userAlbumsPersistedByUserIdAndAlbumId.size() == 2) {
                    break;
                }
            }
        }
        return userAlbumsPersistedByUserIdAndAlbumId;
    }

    public UserAlbum getUserAlbum() {
        return userAlbum;
    }
}
