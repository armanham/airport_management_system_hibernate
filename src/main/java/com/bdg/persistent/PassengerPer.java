package com.bdg.persistent;

import javax.persistence.*;

@Entity
@Table(name = "passenger")
public class PassengerPer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 24)
    private String name;

    @Column(nullable = false, length = 24, unique = true)
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = true)
    private AddressPer addressPer;


    public PassengerPer() {
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

    public AddressPer getAddress() {
        return addressPer;
    }

    public void setAddress(final AddressPer addressPer) {
        this.addressPer = addressPer;
    }
}