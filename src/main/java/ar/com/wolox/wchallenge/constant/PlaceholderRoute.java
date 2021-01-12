package ar.com.wolox.wchallenge.constant;

public enum PlaceholderRoute {

    ROUTE_SERVICE("https://jsonplaceholder.typicode.com/"),
    USERS("users/"),
    PHOTOS("photos/"),
    ALBUMS("albums/"),
    COMMENTS_BY_NAME("comments?name="),
    ALBUMS_BY_USER_ID("albums?userId="),
    PHOTOS_BY_ALBUM_ID("photos?albumId=");

    private final String route;

    PlaceholderRoute(String route) {
        this.route = route;
    }

    public String getRoute() {
        return this.route;
    }
}