package Model;

public class RouteIncludes implements Comparable {

    private Stop stop;
    private int oreder;

    public Stop getStop() {
        return stop;
    }

    public int getOreder() {
        return oreder;
    }

    public RouteIncludes(Stop stop, int oreder) {
        this.stop = stop;
        this.oreder = oreder;
    }

    @Override
    public int compareTo(Object o) {
        return Integer.compare(this.oreder, ((RouteIncludes)o).oreder);
    }

    @Override
    public String toString() {
        return oreder + "- " + stop.getName();
    }

}
