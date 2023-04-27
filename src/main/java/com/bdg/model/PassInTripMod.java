package com.bdg.model;

import com.bdg.validator.Validator;

import java.sql.Timestamp;
import java.util.Objects;


public class PassInTripMod {

    private int id;
    private TripMod trip;
    private PassengerMod passenger;
    private String place;
    private Timestamp time;


    public PassInTripMod(
            final TripMod trip,
            final PassengerMod passenger,
            final String place,
            final Timestamp time) {
        setTrip(trip);
        setPassenger(passenger);
        setPlace(place);
        setTime(time);
    }

    public PassInTripMod() {
    }


    public int getId() {
        return id;
    }

    public void setId(final int id) {
        Validator.checkId(id);
        this.id = id;
    }

    public TripMod getTrip() {
        return trip;
    }

    public void setTrip(final TripMod trip) {
        Validator.checkNull(trip);
        this.trip = trip;
    }

    public PassengerMod getPassenger() {
        return passenger;
    }

    public void setPassenger(final PassengerMod passenger) {
        Validator.checkNull(passenger);
        this.passenger = passenger;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(final String place) {
        Validator.validateString(place);
        this.place = place;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(final Timestamp time) {
        Validator.checkNull(time);
        this.time = time;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PassInTripMod that = (PassInTripMod) o;
        return Objects.equals(trip, that.trip) && Objects.equals(passenger, that.passenger) && Objects.equals(place, that.place);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trip, passenger, place);
    }


    @Override
    public String toString() {
        return "PassInTripMod{" +
                "id=" + id +
                ", trip=" + trip +
                ", passenger=" + passenger +
                ", time=" + time +
                ", place='" + place + '\'' +
                '}';
    }
}