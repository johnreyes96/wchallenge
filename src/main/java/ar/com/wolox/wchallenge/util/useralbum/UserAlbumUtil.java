package ar.com.wolox.wchallenge.util.useralbum;

import ar.com.wolox.wchallenge.constant.PermissionEnum;
import ar.com.wolox.wchallenge.model.UserAlbum;

import java.util.ArrayList;
import java.util.List;

public class UserAlbumUtil {

    public static List<String> getPermissionsAllowed() {
        List<String> permissionsAllowed = new ArrayList<>();
        permissionsAllowed.add(PermissionEnum.WRITE.getPermission());
        permissionsAllowed.add(PermissionEnum.READ.getPermission());
        return permissionsAllowed;
    }

    public static List<UserAlbum> createUserAlbums(UserAlbum userAlbum) {
        List<UserAlbum> userAlbums = new ArrayList<>();
        userAlbums.add(userAlbum);
        if (PermissionEnum.WRITE.getPermission().equals(userAlbum.getPermission())) {
            userAlbums.add(createUserAlbumWithReadPermission(userAlbum));
        }
        return userAlbums;
    }

    private static UserAlbum createUserAlbumWithReadPermission(UserAlbum userAlbum) {
        UserAlbum userAlbumRead = new UserAlbum();
        userAlbumRead.setAlbumId(userAlbum.getAlbumId());
        userAlbumRead.setUserId(userAlbum.getUserId());
        userAlbumRead.setPermission(PermissionEnum.READ.getPermission());
        return userAlbumRead;
    }
}
