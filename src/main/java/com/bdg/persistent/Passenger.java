package com.bdg.persistent;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "passenger")
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 24)
    private String name;

    @Column(nullable = false, length = 24, unique = true)
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
    private Address address;

    @ManyToMany
    @JoinTable(
            name = "pass_in_trip",
            joinColumns = {@JoinColumn(name = "pass_id")},
            inverseJoinColumns = {@JoinColumn(name = "trip_id")}
    )
    private Set<Trip> trips;

    public Passenger() {
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(final Address address) {
        this.address = address;
    }
}