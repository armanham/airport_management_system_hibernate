package com.bdg.service;

import com.bdg.model.TripMod;
import com.bdg.repository.TripRepository;

import java.util.List;
import java.util.Set;

public class TripService implements TripRepository {
    @Override
    public List<TripMod> getAllFrom(String city) {
        return null;
    }

    @Override
    public List<TripMod> getAllTo(String city) {
        return null;
    }

    @Override
    public TripMod getBy(int id) {
        return null;
    }

    @Override
    public Set<TripMod> getAll() {
        return null;
    }

    @Override
    public Set<TripMod> get(int offset, int perPage, String sort) {
        return null;
    }

    @Override
    public TripMod save(TripMod item) {
        return null;
    }

    @Override
    public boolean updateBy(int id, TripMod item) {
        return false;
    }

    @Override
    public boolean deleteBy(int id) {
        return false;
    }
}
