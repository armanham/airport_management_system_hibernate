package com.bdg.repository;

import com.bdg.model.Trip;
import com.bdg.repository.common.CommonRepository;

import java.util.List;

public interface TripRepository extends CommonRepository<Trip> {

    List<Trip> getAllFrom(String city);

    List<Trip> getAllTo(String city);
}