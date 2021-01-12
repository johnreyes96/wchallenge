package ar.com.wolox.wchallenge.util.useralbum;

import ar.com.wolox.wchallenge.dto.UserPermissionToAlbumDTO;
import ar.com.wolox.wchallenge.exception.DuplicateRegisterException;
import ar.com.wolox.wchallenge.exception.PermissionNotFoundException;
import ar.com.wolox.wchallenge.mock.UserAlbumMock;
import ar.com.wolox.wchallenge.model.UserAlbum;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserAlbumValidatorTest {

    private UserAlbumValidator userAlbumValidator;
    private UserAlbum userAlbum;

    @BeforeEach
    void setUp() {
        this.userAlbum = UserAlbumMock.getUserAlbumBasic();
        this.userAlbumValidator = new UserAlbumValidator(userAlbum);
    }

    @AfterEach
    void tearDown() {
        this.userAlbum = null;
        this.userAlbumValidator = null;
    }

    @Test
    void validateUserAlbumPersistedToCreateWhenNoExistUserAlbumNoMustThrowDuplicateRegisterExceptionTest() {
        List<UserAlbum> userAlbums = new ArrayList<>();
        UserAlbum userAlbum = UserAlbumMock.getUserAlbumReadPermission();
        userAlbums.add(userAlbum);

        userAlbumValidator.validateUserAlbumPersistedToCreate(userAlbums);
    }

    @Test
    void validateUserAlbumPersistedToCreateWhenExistUserAlbumMustThrowDuplicateRegisterExceptionTest() {
        List<UserAlbum> userAlbums = new ArrayList<>();
        UserAlbum userAlbum = UserAlbumMock.getUserAlbumBasic();
        userAlbums.add(userAlbum);
        String expectedMessage = "The user 1 already has permissions for album 1";

        Exception exception = assertThrows(DuplicateRegisterException.class, () -> {
            userAlbumValidator.validateUserAlbumPersistedToCreate(userAlbums);
        });

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void validateUserAlbumPersistedToUpdateWhenExistUserAlbumNoMustThrowPermissionNoFoundExceptionTest() {
        List<UserAlbum> userAlbums = new ArrayList<>();
        userAlbums.add(UserAlbumMock.getUserAlbumBasic());

        userAlbumValidator.validateUserAlbumPersistedToUpdate(userAlbums);
    }

    @Test
    void validateUserAlbumPersistedToUpdateWhenNotExistUserAlbumMustThrowPermissionNoFoundExceptionTest() {
        List<UserAlbum> userAlbums = new ArrayList<>();
        userAlbums.add(UserAlbumMock.getUserAlbumReadPermission());
        String expectedMessage = "The user 1 does not have permissions for album 1";

        Exception exception = assertThrows(PermissionNotFoundException.class, () -> {
            userAlbumValidator.validateUserAlbumPersistedToUpdate(userAlbums);
        });

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void getUserAlbumsPersistedByUserIdAndAlbumIdWhenThereAreNotUserAlbumsPersistedMustReturnAnEmptyListTest() {
        List<UserAlbum> userAlbums = new ArrayList<>();

        List<UserAlbum> userAlbumExpected = userAlbumValidator.getUserAlbumsPersistedByUserIdAndAlbumId(userAlbums);

        assertTrue(userAlbumExpected.isEmpty());
    }

    @Test
    void getUserAlbumsPersistedByUserIdAndAlbumIdWhenNoExistUserAlbumMustReturnAnEmptyListTest() {
        List<UserAlbum> userAlbums = new ArrayList<>();
        userAlbums.add(UserAlbumMock.getUserAlbumReadPermission());
        userAlbums.add(UserAlbumMock.getUserAlbumWritePermission());

        List<UserAlbum> userAlbumExpected = userAlbumValidator.getUserAlbumsPersistedByUserIdAndAlbumId(userAlbums);

        assertTrue(userAlbumExpected.isEmpty());
    }

    @Test
    void getUserAlbumsPersistedByUserIdAndAlbumIdWhenExistUserAlbumReadPermissionMustReturnOneUserAlbumsPersistedTest() {
        List<UserAlbum> userAlbums = new ArrayList<>();
        userAlbums.add(UserAlbumMock.getUserAlbumBasic());
        userAlbums.add(UserAlbumMock.getUserAlbumReadPermission());
        userAlbums.add(UserAlbumMock.getUserAlbumWritePermission());

        List<UserAlbum> userAlbumExpected = userAlbumValidator.getUserAlbumsPersistedByUserIdAndAlbumId(userAlbums);

        assertEquals(1, userAlbumExpected.size());
    }

    @Test
    void getUserAlbumsPersistedByUserIdAndAlbumIdWhenExistUserAlbumReadAndWritePermissionMustReturnTwoUserAlbumsPersistedTest() {
        UserAlbum userAlbum = UserAlbumMock.getUserAlbumWritePermission();
        List<UserAlbum> userAlbums = new ArrayList<>();
        userAlbums.add(UserAlbumMock.getUserAlbumBasic());
        userAlbums.add(UserAlbumMock.getUserAlbumReadPermission());
        userAlbums.add(UserAlbumMock.getUserAlbumWritePermission());
        this.userAlbumValidator = new UserAlbumValidator(userAlbum);

        List<UserAlbum> userAlbumExpected = userAlbumValidator.getUserAlbumsPersistedByUserIdAndAlbumId(userAlbums);

        assertEquals(2, userAlbumExpected.size());
    }

    @Test
    void getUserPermissionToAlbumDTOByAlbumIdAndPermissionWhenHaveThereAreNoUserPermissionToAlbumPersistedMustReturnAnEmptyListTest() {
        List<UserAlbum> userAlbums = new ArrayList<>();

        List<UserPermissionToAlbumDTO> UserPermissionToAlbumDTOExpected =
                userAlbumValidator.getUserPermissionToAlbumDTOByAlbumIdAndPermission(userAlbums,
                        this.userAlbum.getAlbumId(), this.userAlbum.getPermission());

        assertTrue(UserPermissionToAlbumDTOExpected.isEmpty());
    }

    @Test
    void getUserPermissionToAlbumDTOByAlbumIdAndPermissionWhenNotUserHaveNotPermissionToAlbumMustReturnAnEmptyListTest() {
        List<UserAlbum> userAlbums = new ArrayList<>();
        userAlbums.add(UserAlbumMock.getUserAlbumReadPermission());
        userAlbums.add(UserAlbumMock.getUserAlbumWritePermission());

        List<UserPermissionToAlbumDTO> UserPermissionToAlbumDTOExpected =
                userAlbumValidator.getUserPermissionToAlbumDTOByAlbumIdAndPermission(userAlbums,
                        this.userAlbum.getAlbumId(), this.userAlbum.getPermission());

        assertTrue(UserPermissionToAlbumDTOExpected.isEmpty());
    }

    @Test
    void getUserPermissionToAlbumDTOByAlbumIdAndPermissionWhenExistUsersPermissionToAlbumMustReturnUsersTest() {
        List<UserAlbum> userAlbums = new ArrayList<>();
        userAlbums.add(UserAlbumMock.getUserAlbumBasic());
        userAlbums.add(UserAlbumMock.getUserAlbumReadPermission());
        userAlbums.add(UserAlbumMock.getUserAlbumWritePermission());

        List<UserPermissionToAlbumDTO> UserPermissionToAlbumDTOExpected =
                userAlbumValidator.getUserPermissionToAlbumDTOByAlbumIdAndPermission(userAlbums,
                        this.userAlbum.getAlbumId(), this.userAlbum.getPermission());

        assertEquals(1, UserPermissionToAlbumDTOExpected.size());
    }

    @Test
    void getUserAlbum() {
        UserAlbum userAlbumExpected = userAlbumValidator.getUserAlbum();

        assertSame(this.userAlbum, userAlbumExpected);
    }
}