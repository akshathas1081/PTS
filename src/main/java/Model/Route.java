package Model;

import java.util.List;
import java.util.Objects;

public class Route {

    private String id;
    private List<RouteIncludes> includesList;
    private List<TransportPrice> priceList;
    private List<String> departures0;
    private List<String> departures1;

    public String getId() {
        return id;
    }

    public Route(String routeId) {
        this.id = routeId;
    }

    public List<RouteIncludes> getIncludesList() {
        return includesList;
    }

    public void setIncludesList(List<RouteIncludes> includesList) {
        this.includesList = includesList;
    }

    public List<TransportPrice> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<TransportPrice> priceList) {
        this.priceList = priceList;
    }

    public List<String> getDepartures0() {
        return departures0;
    }

    public void setDepartures0(List<String> departures0) {
        this.departures0 = departures0;
    }

    public List<String> getDepartures1() {
        return departures1;
    }

    public void setDepartures1(List<String> departures1) {
        this.departures1 = departures1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return id.equals(route.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id;
    }
}