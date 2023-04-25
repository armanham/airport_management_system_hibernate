package com.bdg.model;

import java.sql.Timestamp;

public class TripMod {

    private int tripNumber;
    private CompanyMod companyMod;
    private String airplane;
    private String townFrom;
    private String townTo;
    private Timestamp timeOut;
    private Timestamp timeIn;

    public TripMod(final int tripNumber,
                   final CompanyMod companyMod,
                   final String airplane,
                   final String townFrom,
                   final String townTo,
                   final Timestamp timeOut,
                   final Timestamp timeIn) {
        setTripNumber(tripNumber);
        setCompany(companyMod);
        setAirplane(airplane);
        setTownFrom(townFrom);
        setTownTo(townTo);
        setTimeOut(timeOut);
        setTimeIn(timeIn);
    }

    public TripMod() {
    }

    public int getTripNumber() {
        return tripNumber;
    }

    public void setTripNumber(final int tripNumber) {
        if (tripNumber <= 0) {
            throw new IllegalArgumentException("'tripNumber' must be positive number: ");
        }
        this.tripNumber = tripNumber;
    }

    public CompanyMod getCompany() {
        return companyMod;
    }

    public void setCompany(final CompanyMod companyMod) {
        checkNull(companyMod);
        this.companyMod = companyMod;
    }

    public String getAirplane() {
        return airplane;
    }

    public void setAirplane(final String airplane) {
        validateString(airplane);
        this.airplane = airplane;
    }

    public String getTownFrom() {
        return townFrom;
    }

    public void setTownFrom(final String townFrom) {
        validateString(townFrom);
        this.townFrom = townFrom;
    }

    public String getTownTo() {
        return townTo;
    }

    public void setTownTo(final String townTo) {
        validateString(townTo);
        this.townTo = townTo;
    }

    public Timestamp getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(final Timestamp timeOut) {
        checkNull(timeOut);
        this.timeOut = timeOut;
    }

    public Timestamp getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(final Timestamp timeIn) {
        checkNull(timeIn);
        this.timeIn = timeIn;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "tripNumber=" + tripNumber +
                ", company=" + companyMod +
                ", airplane='" + airplane + '\'' +
                ", townFrom='" + townFrom + '\'' +
                ", townTo='" + townTo + '\'' +
                ", timeOut=" + timeOut +
                ", timeIn=" + timeIn +
                "}\n";
    }

    private void validateString(final String string) {
        if (string == null || string.isEmpty()) {
            throw new IllegalArgumentException("Passed null or empty value: ");
        }
    }

    private void checkNull(final Object obj) {
        if (obj == null) {
            throw new NullPointerException("Passed null value: ");
        }
    }
}