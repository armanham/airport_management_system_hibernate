package com.bdg.repository;

import com.bdg.model.PassengerMod;
import com.bdg.repository.common.CommonRepository;

import java.util.List;

public interface PassengerRepository extends CommonRepository<PassengerMod> {

    List<PassengerMod> getAllOf(int tripId);

    boolean registerTrip(int id, int tripId);

    boolean cancelTrip(int id, int tripId);
}