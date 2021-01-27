package Model;

public class UserInfo {

    private static User user;
    private static Passenger passenger;
    private static Card card;

    private UserInfo() {}

    public static User getUser() {
        return user;
    }

    protected static void setUser(User user) {
        UserInfo.user = user;
    }

    public static Passenger getPassenger() {
        return passenger;
    }

    protected static void setPassenger(Passenger passenger) {
        UserInfo.passenger = passenger;
    }

    public static Card getCard() {
        return card;
    }

    protected static void setCard(Card card) {
        UserInfo.card = card;
    }

}
