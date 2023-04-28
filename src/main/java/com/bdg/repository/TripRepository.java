package com.bdg.repository;

import com.bdg.model.TripMod;
import com.bdg.repository.common.CommonRepository;

import java.util.List;

public interface TripRepository extends CommonRepository<TripMod> {

    List<TripMod> getAllFrom(String city);

    List<TripMod> getAllTo(String city);

    boolean updateBy(int id, TripMod item);
}