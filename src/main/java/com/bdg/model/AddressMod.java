package com.bdg.model;

import com.bdg.model.common.BaseMod;
import com.bdg.validator.Validator;

import java.util.Objects;

public class AddressMod extends BaseMod {

    private String country;
    private String city;

    public AddressMod(final String country, final String city) {
        setCountry(country);
        setCity(city);
    }

    public AddressMod() {
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        Validator.validateString(country);
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        Validator.validateString(city);
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressMod that = (AddressMod) o;
        return Objects.equals(country, that.country) && Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, city);
    }

    @Override
    public String toString() {
        return "AddressMod{" +
                ", id=" + getId() +
                "country='" + country + '\'' +
                ", city='" + city + '\'' +
                "}\n";
    }
}