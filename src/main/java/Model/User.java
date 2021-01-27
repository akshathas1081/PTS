package Model;

import java.util.LinkedList;
import java.util.List;

public class User {

    private Login login;
    private String lastLogin;
    private List<Review> reviewList = new LinkedList<>();

    public User(String email, String password, String lastLogin) {
        this.login = new Login(email, password);
        this.lastLogin = lastLogin;
    }

    public String getEmail() {
        return login.getEmail();
    }

    public String getPassword() {
        return login.getPassword();
    }


    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

}
