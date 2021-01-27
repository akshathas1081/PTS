package Model;

import java.util.LinkedList;
import java.util.List;

public class Passenger {

    private String passingerId;
    private String fullName;
    private String birthDate;
    private List<Address> addressList = new LinkedList<>();
    private List<Phone> phoneList = new LinkedList<>();

    public Passenger(String passingerId, String fullName, String birthDate) {
        this.passingerId = passingerId;
        this.fullName = fullName;
        this.birthDate = birthDate;
    }

    public String getPassingerId() {
        return passingerId;
    }

    public void setPassingerId(String passingerId) {
        this.passingerId = passingerId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    public List<Phone> getPhoneList() {
        return phoneList;
    }

    public void setPhoneList(List<Phone> phoneList) {
        this.phoneList = phoneList;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "passingerId='" + passingerId + '\'' +
                ", fullName='" + fullName + '\'' +
                ", birthDate='" + birthDate + '\'' +
                '}';
    }

}