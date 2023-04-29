package com.bdg.converter.persistent_to_model;

import com.bdg.model.PassInTripMod;
import com.bdg.persistent.PassInTripPer;
import com.bdg.validator.Validator;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

public class PerToModPassInTrip extends PerToMod<PassInTripPer, PassInTripMod> {

    private static final PerToModTrip PER_TO_MOD_TRIP = new PerToModTrip();
    private static final PerToModPassenger PER_TO_MOD_PASSENGER = new PerToModPassenger();


    @Override
    public PassInTripMod getModelFrom(PassInTripPer persistent) {
        Validator.checkNull(persistent);

        PassInTripMod model = new PassInTripMod();
        model.setId(persistent.getId());
        model.setPassenger(PER_TO_MOD_PASSENGER.getModelFrom(persistent.getPassenger()));
        model.setTrip(PER_TO_MOD_TRIP.getModelFrom(persistent.getTrip()));
        model.setPlace(persistent.getPlace());
        model.setTime(persistent.getTime());
        return null;
    }


    @Override
    public Collection<PassInTripMod> getModelListFrom(Collection<PassInTripPer> persistentList) {
        Validator.checkNull(persistentList);

        Set<PassInTripMod> modelSet = new LinkedHashSet<>(persistentList.size());
        for (PassInTripPer tempPassInTripPer : persistentList) {
            modelSet.add(getModelFrom(tempPassInTripPer));
        }
        return modelSet;
    }
}