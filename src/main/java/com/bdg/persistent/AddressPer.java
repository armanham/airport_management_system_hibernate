package com.bdg.persistent;

import javax.persistence.*;

//TODO Karanq sax Entity-neri hamar sarqenq mi hat abstract class,,,,
// id-n saxi hamar ira getter/setter-nerov nuynna

@Entity
@Table(
        name = "address",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"country", "city"})}
)
public class AddressPer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 50)
    private String country;

    @Column(nullable = false, length = 50)
    private String city;

    public AddressPer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }
}