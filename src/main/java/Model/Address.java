package Model;

import java.util.Objects;

public class Address {

    private String id;
    private String town;
    private String neighborhood;
    private String street;
    private String number;
    private int coorX;
    private int coorY;

    public Address() {
        this("unk", "unk", "unk", "unk", 0, 0);
    }

    public Address(String id, String town, String neighborhood, String street, int coorX, int coorY) {
        this(id, town, neighborhood, street, "0", coorX, coorY);
    }

    public Address(String id, String town, String neighborhood, String street, String number, int coorX, int coorY) {
        this.id = id;
        this.town = town;
        this.neighborhood = neighborhood;
        this.street = street;
        this.number = number;
        this.coorX = coorX;
        this.coorY = coorY;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getCoorX() {
        return coorX;
    }

    public void setCoorX(int coorX) {
        this.coorX = coorX;
    }

    public int getCoorY() {
        return coorY;
    }

    public void setCoorY(int coorY) {
        this.coorY = coorY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return id.equals(address.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Address{" +
                "id='" + id + '\'' +
                ", town='" + town + '\'' +
                ", neighborhood='" + neighborhood + '\'' +
                ", street='" + street + '\'' +
                ", coorX=" + coorX +
                ", coorY=" + coorY +
                '}';
    }

}