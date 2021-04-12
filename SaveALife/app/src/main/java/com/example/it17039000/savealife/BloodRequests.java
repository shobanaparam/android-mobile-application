package com.example.it17039000.savealife;

public class BloodRequests {
    int id;
    String name,bloodGroup,hospital,place,unit,phone;

    public BloodRequests(int id, String name, String bloodGroup, String unit, String hospital, String phone, String place) {
        this.id = id;
        this.name = name;
        this.bloodGroup = bloodGroup;
        this.unit = unit;
        this.hospital = hospital;
        this.phone = phone;
        this.place = place;
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

    public String getUnit() {
        return unit;
    }

    public String getHospital() {
        return hospital;
    }

    public String getPhone() {
        return phone;
    }

    public String getPlace() {
        return place;
    }
}
