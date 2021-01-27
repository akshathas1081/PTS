package Model;

import java.util.List;
import java.util.Objects;

public class Stop {

    private String id;
    private String name;
    private Address address;
    private List<Route> routeList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Route> getRouteList() {
        return routeList;
    }

    public void setRouteList(List<Route> routeList) {
        this.routeList = routeList;
    }

    public Stop() {
        this("unk", "unk", "unk", "unk", "unk", 0, 0);
    }

    public Stop(String id, String name, String town, String neighborhood, String street, int coorX, int coorY) {
        this(id, name, new Address(id, town, neighborhood, street, coorX, coorY));
    }

    public Stop(String id, String name, Address address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stop stop = (Stop) o;
        return id.equals(stop.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return name;
    }

}