package Model;

import java.util.List;

public class Contact {
    private String name;
    private List<Phone> phoneList;

    public Contact(String name, List<Phone> phoneList) {
        this.name = name;
        this.phoneList = phoneList;
    }

    public Contact() {
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Phone> getPhoneList() {
        return this.phoneList;
    }

    public void setPhoneList(List<Phone> phoneList) {
        this.phoneList = phoneList;
    }


    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", phoneList='" + getPhoneList() + "'" +
            "}";
    }


}
