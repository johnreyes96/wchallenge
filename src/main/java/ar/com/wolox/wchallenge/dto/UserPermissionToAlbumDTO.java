package ar.com.wolox.wchallenge.dto;

public class UserPermissionToAlbumDTO {

    private short userId;

    public UserPermissionToAlbumDTO(short userId) {
        this.userId = userId;
    }

    public short getUserId() {
        return userId;
    }

    public void setUserId(short userId) {
        this.userId = userId;
    }
}