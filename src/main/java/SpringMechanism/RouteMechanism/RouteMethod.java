package SpringMechanism.RouteMechanism;

import java.util.Objects;

public class RouteMethod {
    private final String route;
    private final HttpMethod httpMethod;

    public RouteMethod(String route, HttpMethod httpMethod) {
        this.route = route;
        this.httpMethod = httpMethod;
    }

    public String getRoute() {
        return route;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        return this.route.equalsIgnoreCase(((RouteMethod) o).route) && this.httpMethod.equals(((RouteMethod) o).httpMethod);
    }

    @Override
    public int hashCode() {
        return Objects.hash(route, httpMethod);
    }
}
