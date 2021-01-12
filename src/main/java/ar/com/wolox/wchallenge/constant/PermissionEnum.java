package ar.com.wolox.wchallenge.constant;

public enum PermissionEnum {

    READ("READ"),
    WRITE("WRITE");

    private final String permission;

    PermissionEnum(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}