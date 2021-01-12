package ar.com.wolox.wchallenge.util.useralbum;

import ar.com.wolox.wchallenge.constant.PermissionEnum;
import ar.com.wolox.wchallenge.mock.UserAlbumMock;
import ar.com.wolox.wchallenge.model.UserAlbum;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserAlbumUtilTest {

    @Test
    void getPermissionsAllowedTest() {
        List<String> permissionsAllowed = UserAlbumUtil.getPermissionsAllowed();

        assertEquals(2, permissionsAllowed.size());
    }

    @Test
    void createUserAlbumsWhenIsReadPermissionMustReturnOneUserAlbumWithReadPermissionTest() {
        UserAlbum userAlbum = UserAlbumMock.getUserAlbumReadPermission();


        List<UserAlbum> userAlbums = UserAlbumUtil.createUserAlbums(userAlbum);

        assertEquals(1, userAlbums.size());
    }

    @Test
    void createUserAlbumsWhenIsWritePermissionMustReturnTwoUserAlbumWithReadAndWritePermissionsTest() {
        UserAlbum userAlbum = UserAlbumMock.getUserAlbumWritePermission();

        List<UserAlbum> userAlbums = UserAlbumUtil.createUserAlbums(userAlbum);

        assertEquals(2, userAlbums.size());
    }

    @Test
    void createUserAlbumWithOnlyPermissionTest() {
        String permission = PermissionEnum.READ.getPermission();

        UserAlbum userAlbum = UserAlbumUtil.createUserAlbumWithOnlyPermission(permission);

        assertEquals(permission, userAlbum.getPermission());
    }
}