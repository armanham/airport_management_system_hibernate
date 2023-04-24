package com.bdg.insert_into_db;

import com.bdg.persistent.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

public class Inserter {

    private static final String ROOT_PATH =
            "C:\\Users\\user\\Java Projects\\airport_management_system_hibernate\\src\\main\\resources\\txt\\";
    private static final Path PATH_COMPANY_TXT = Path.of(ROOT_PATH + "companies.txt");
    private static final Path PATH_ADDRESS_TXT = Path.of(ROOT_PATH + "addresses.txt");
    private static final Path PATH_PASSENGER_TXT = Path.of(ROOT_PATH + "passengers.txt");
    private static final Path PATH_TRIP_TXT = Path.of(ROOT_PATH + "trip.txt");
    private static final Path PATH_PASSINTRIP_TXT = Path.of(ROOT_PATH + "pass_in_trip.txt");


    private Session session;


    public void insertCompanyTable() {
        Transaction transaction = null;
        List<String> lines = readLinesOfFileFrom(PATH_COMPANY_TXT);

        try {
            transaction = session.beginTransaction();

            for (int i = 0; i < (lines != null ? lines.size() : 0); i++) {
                String line = lines.get(i);
                String[] fields = line.split(",");

                String[] dateParts = fields[1].split("/");

                Company company = new Company();
                company.setName(fields[1]);
                company.setFoundDate(
                        Date.valueOf(
                                LocalDate.of(
                                        Integer.parseInt(dateParts[2]),
                                        Integer.parseInt(dateParts[0]),
                                        Integer.parseInt(dateParts[1])
                                )));

                session.save(company);
            }

            transaction.commit();
        } catch (Exception e) {
            assert transaction != null;
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }


    public void insertAddressTable() {
        Transaction transaction = null;
        List<String> lines = readLinesOfFileFrom(PATH_ADDRESS_TXT);

        try {
            transaction = session.beginTransaction();

            for (int i = 0; i < (lines != null ? lines.size() : 0); i++) {
                String line = lines.get(i);
                String[] fields = line.split(",");

                Address address = new Address();
                address.setCountry(fields[0]);
                address.setCity(fields[1]);

                session.save(address);
            }

            transaction.commit();
        } catch (Exception e) {
            assert transaction != null;
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }


    public void insertPassengerTable() {
        Transaction transaction = null;
        List<String> lines = readLinesOfFileFrom(PATH_PASSENGER_TXT);

        try {
            transaction = session.beginTransaction();

            for (int i = 0; i < (lines != null ? lines.size() : 0); i++) {
                String line = lines.get(i);
                String[] fields = line.split(",");

                Address address = session.get(Address.class, Integer.parseInt(fields[2]));
                if (address == null) {
                    transaction.rollback();
                    return;
                }

                Passenger passenger = new Passenger();
                passenger.setName(fields[0]);
                passenger.setPhone(fields[1]);
                passenger.setAddress(address);

                session.save(passenger);
            }

            transaction.commit();
        } catch (Exception e) {
            assert transaction != null;
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }


    public void insertTripTable() {
        Transaction transaction = null;
        List<String> lines = readLinesOfFileFrom(PATH_TRIP_TXT);

        try {
            transaction = session.beginTransaction();

            for (int i = 0; i < (lines != null ? lines.size() : 0); i++) {
                String line = lines.get(i);
                String[] fields = line.split(",");

                Company company = session.get(Company.class, Integer.parseInt(fields[1]));
                if (company == null) {
                    transaction.rollback();
                    return;
                }

                Trip trip = new Trip();
                trip.setTripNumber(Integer.parseInt(fields[0]));
                trip.setAirplane(fields[2]);
                trip.setTimeIn(Timestamp.valueOf(fields[5]));
                trip.setTimeOut(Timestamp.valueOf(fields[6]));
                trip.setTownFrom(fields[3]);
                trip.setTownTo(fields[4]);
                trip.setCompany(company);

                session.save(trip);
            }

            transaction.commit();
        } catch (Exception e) {
            assert transaction != null;
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }


    public void insertPassInTripTable() {
        Transaction transaction = null;
        List<String> lines = readLinesOfFileFrom(PATH_PASSINTRIP_TXT);

        try {
            transaction = session.beginTransaction();

            for (int i = 0; i < (lines != null ? lines.size() : 0); i++) {
                String line = lines.get(i);
                String[] fields = line.split(",");

                Trip trip = session.get(Trip.class, Integer.parseInt(fields[0]));
                if (trip == null) {
                    transaction.rollback();
                    return;
                }

                Passenger passenger = session.get(Passenger.class, Integer.parseInt(fields[1]));
                if (passenger == null) {
                    transaction.rollback();
                    return;
                }

                PassInTrip passInTrip = new PassInTrip();
                passInTrip.setTrip(trip);
                passInTrip.setPassenger(passenger);
                passInTrip.setTime(Timestamp.valueOf(fields[2]));
                passInTrip.setPlace(fields[3]);

                session.save(passInTrip);
            }

            transaction.commit();
        } catch (Exception e) {
            assert transaction != null;
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }


    private List<String> readLinesOfFileFrom(Path path) {
        if (path == null) {
            throw new NullPointerException("Passed null value as 'path': ");
        }
        try {
            return Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void setSession(Session session) {
        if (session == null) {
            throw new NullPointerException("Passed null value as 'session': ");
        }
        this.session = session;
    }
}