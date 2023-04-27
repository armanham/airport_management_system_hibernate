package com.bdg.model;

import com.bdg.validator.Validator;

public class PassengerMod {

    private int id;
    private String name;
    private String phone;
    private AddressMod addressMod;


    public PassengerMod(final String name,
                        final String phone,
                        final AddressMod addressMod) {
        setName(name);
        setPhone(phone);
        setAddress(addressMod);
    }

    public PassengerMod() {
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        Validator.checkId(id);
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        validateString(name);
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(final String phone) {
        validateString(phone);
        this.phone = phone;
    }

    public AddressMod getAddress() {
        return addressMod;
    }

    public void setAddress(final AddressMod addressMod) {
        if (addressMod == null) {
            throw new NullPointerException("Passed null value as 'address': ");
        }
        this.addressMod = addressMod;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address=" + addressMod +
                "}\n";
    }

    private void validateString(final String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Passed null or empty value: ");
        }
    }
}