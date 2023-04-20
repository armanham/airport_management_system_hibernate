package com.bdg.insert_into_db;

import com.bdg.persistent.Address;
import com.bdg.persistent.Company;
import com.bdg.persistent.Passenger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Date;
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

        try {
            transaction = session.beginTransaction();

            List<String> lines = readLinesOfFileFrom(PATH_COMPANY_TXT);

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

        try {
            transaction = session.beginTransaction();

            List<String> lines = readLinesOfFileFrom(PATH_ADDRESS_TXT);

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

        try {
            transaction = session.beginTransaction();
            List<String> lines = readLinesOfFileFrom(PATH_PASSENGER_TXT);

            for (int i = 0; i < (lines != null ? lines.size() : 0); i++) {
                String line = lines.get(i);
                String[] fields = line.split(",");

                String hql = "select ad from Address ad where ad.id = :id";
                Query<Address> query = session.createQuery(hql);
                query.setParameter("id", Integer.parseInt(fields[2]));

                List<Address> result = query.getResultList();
                if (result.isEmpty()){
                    return;
                }

                Passenger passenger = new Passenger();
                passenger.setName(fields[0]);
                passenger.setPhone(fields[1]);
                passenger.setAddress(result.get(0));

                session.save(passenger);
            }

            transaction.commit();
        } catch (Exception e) {
            assert transaction != null;
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }
//
//
//    public void insertTripTable() {
//
//        PreparedStatement pst = null;
//
//        try {
//            List<String> lines = readLinesOfFileFrom(PATH_TRIP_TXT);
//
//            for (int i = 0; i < (lines != null ? lines.size() : 0); i++) {
//                String line = lines.get(i);
//                String[] fields = line.split(",");
//
//                pst = con.prepareStatement(INSERT_INTO_TRIP_SQL);
//
//                pst.setInt(1, Integer.parseInt(fields[0]));
//                pst.setInt(2, Integer.parseInt(fields[1]));
//                pst.setString(3, fields[2]);
//                pst.setString(4, fields[3]);
//                pst.setString(5, fields[4]);
//                pst.setTimestamp(6, Timestamp.valueOf(fields[5]));
//                pst.setTimestamp(7, Timestamp.valueOf(fields[6]));
//
//                pst.executeUpdate();
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            try {
//                assert pst != null;
//                pst.close();
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
//
//
//    public void insertPassInTripTable() {
//
//        PreparedStatement pst = null;
//
//        try {
//            List<String> lines = readLinesOfFileFrom(PATH_PASSINTRIP_TXT);
//
//            for (int i = 0; i < (lines != null ? lines.size() : 0); i++) {
//                String line = lines.get(i);
//                String[] fields = line.split(",");
//
//                pst = con.prepareStatement(INSERT_INTO_PASSINTRIP_SQL);
//
//                pst.setInt(1, Integer.parseInt(fields[0]));
//                pst.setInt(2, Integer.parseInt(fields[1]));
//                pst.setTimestamp(3, Timestamp.valueOf(fields[2]));
//                pst.setString(4, fields[3]);
//
//                pst.executeUpdate();
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            try {
//                assert pst != null;
//                pst.close();
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }


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