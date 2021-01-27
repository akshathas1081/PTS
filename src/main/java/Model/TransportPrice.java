package Model;

public class TransportPrice {

    private String cardType;
    private double price;

    public String getCardType() {
        return cardType;
    }

    public double getPrice() {
        return price;
    }

    public TransportPrice(String cardType, double price) {
        this.cardType = cardType;
        this.price = price;
    }

}