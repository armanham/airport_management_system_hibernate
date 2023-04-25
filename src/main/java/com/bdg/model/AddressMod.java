package com.bdg.model;

import java.util.Objects;

public class AddressMod {

    private int id;
    private String country;
    private String city;


    public AddressMod(final String country, final String city) {
        setCountry(country);
        setCity(city);
    }

    public AddressMod() {
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("'id' must be positive number: ");
        }
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        validateString(country);
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        validateString(city);
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
        return "Address{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                "}\n";
    }

    private void validateString(final String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Passed null or empty value: ");
        }
    }
}