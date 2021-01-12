package ar.com.wolox.wchallenge.mock;

import ar.com.wolox.wchallenge.constant.PermissionEnum;
import ar.com.wolox.wchallenge.model.UserAlbum;

public class UserAlbumMock {

    public static UserAlbum getUserAlbumReadPermission() {
        UserAlbum userAlbum = new UserAlbum();
        userAlbum.setUserId((short) 1);
        userAlbum.setAlbumId(2);
        userAlbum.setPermission(PermissionEnum.READ.getPermission());
        return userAlbum;
    }

    public static UserAlbum getUserAlbumWritePermission() {
        UserAlbum userAlbum = new UserAlbum();
        userAlbum.setUserId((short) 1);
        userAlbum.setAlbumId(2);
        userAlbum.setPermission(PermissionEnum.WRITE.getPermission());
        return userAlbum;
    }

    public static UserAlbum getUserAlbumBasic() {
        UserAlbum userAlbum = new UserAlbum();
        userAlbum.setUserId((short) 1);
        userAlbum.setAlbumId(1);
        userAlbum.setPermission(PermissionEnum.READ.getPermission());
        return userAlbum;
    }
}