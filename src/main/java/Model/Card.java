package Model;

import java.util.Objects;

public class Card {

    private String cardId;
    private String cardType;
    private String status;
    private String lastUsage;
    private String issueDate;
    private int travelCount;
    private double balance;

    public Card(String cardId, String cardType, String status, String lastUsage, String issueDate, int travelCount, double balance) {
        this.cardId = cardId;
        this.cardType = cardType;
        this.status = status;
        this.lastUsage = lastUsage;
        this.issueDate = issueDate;
        this.travelCount = travelCount;
        this.balance = balance;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLastUsage() {
        return lastUsage;
    }

    public void setLastUsage(String lastUsage) {
        this.lastUsage = lastUsage;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public int getTravelCount() {
        return travelCount;
    }

    public void setTravelCount(int travelCount) {
        this.travelCount = travelCount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return cardId.equals(card.cardId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardId);
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardId='" + cardId + '\'' +
                ", cardType='" + cardType + '\'' +
                ", status='" + status + '\'' +
                ", lastUsage='" + lastUsage + '\'' +
                ", issueDate='" + issueDate + '\'' +
                ", travelCount=" + travelCount +
                ", balance=" + balance +
                '}';
    }

}