package ar.com.wolox.wchallenge.constant;

public enum PlaceholderRoute {

    ROUTE_SERVICE("https://jsonplaceholder.typicode.com/"),
    USERS("users/");

    private final String route;

    PlaceholderRoute(String route) {
        this.route = route;
    }

    public String getRoute() {
        return this.route;
    }
}
