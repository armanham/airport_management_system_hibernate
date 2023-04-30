package com.bdg.ax2sap.airport_management_system.repository;

import com.bdg.ax2sap.airport_management_system.model.PassengerMod;
import com.bdg.ax2sap.airport_management_system.repository.common.CommonRepository;

import java.util.List;

public interface PassengerRepository extends CommonRepository<PassengerMod> {

    List<PassengerMod> getAllOf(int tripId);

    boolean registerTrip(int id, int tripId);

    boolean cancelTrip(int id, int tripId);

    boolean updateBy(int id, PassengerMod item);
}