package Model;

import java.util.Objects;

public class Phone {

    private String phone;
    private String phoneType;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    public Phone() {
        this("unk", "unk");
    }

    public Phone(String phone, String phoneType) {
        this.phone = phone;
        this.phoneType = phoneType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone1 = (Phone) o;
        return phone.equals(phone1.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phone);
    }

}