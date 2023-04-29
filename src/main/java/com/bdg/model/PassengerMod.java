package com.bdg.model;

import com.bdg.model.common.BaseMod;
import com.bdg.validator.Validator;

public class PassengerMod extends BaseMod {

    private String name;
    private String phone;
    private AddressMod addressMod;

    public PassengerMod(
            final String name,
            final String phone,
            final AddressMod addressMod) {
        setName(name);
        setPhone(phone);
        setAddress(addressMod);
    }

    public PassengerMod() {
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        Validator.validateString(name);
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(final String phone) {
        Validator.validateString(phone);
        this.phone = phone;
    }

    public AddressMod getAddress() {
        return addressMod;
    }

    public void setAddress(final AddressMod addressMod) {
        Validator.checkNull(addressMod);
        this.addressMod = addressMod;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address=" + addressMod +
                "}\n";
    }
}