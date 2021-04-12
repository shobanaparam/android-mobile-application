package com.example.it17039000.savealife;

public class NotificationPanel {

    int id;
    String name,bloodGroup,phone;

    public NotificationPanel(int id, String name, String bloodGroup, String phone) {
        this.id = id;
        this.name = name;
        this.bloodGroup = bloodGroup;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public String getPhone() {
        return phone;
    }

}
