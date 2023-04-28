package com.bdg.repository;

import com.bdg.model.TripMod;
import com.bdg.repository.common.CommonRepository;

import java.sql.Timestamp;
import java.util.Set;

public interface TripRepository extends CommonRepository<TripMod> {

    Set<TripMod> getAllFrom(String city);

    Set<TripMod> getAllTo(String city);

    boolean updateBy(int idToUpdate,
                     String newAirplane, String newTownFrom, String newTownTo,
                     Timestamp newTimeOut,
                     Timestamp newTimeIn);
}