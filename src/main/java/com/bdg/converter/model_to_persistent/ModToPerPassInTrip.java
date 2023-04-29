package com.bdg.converter.model_to_persistent;

import com.bdg.converter.model_to_persistent.common.ModToPer;
import com.bdg.model.PassInTripMod;
import com.bdg.persistent.PassInTripPer;
import com.bdg.validator.Validator;

public class ModToPerPassInTrip extends ModToPer<PassInTripMod, PassInTripPer> {

    private static final ModToPerTrip MOD_TO_PER_TRIP = new ModToPerTrip();
    private static final ModToPerPassenger MOD_TO_PER_PASSENGER = new ModToPerPassenger();


    @Override
    public PassInTripPer getPersistentFrom(PassInTripMod model) {
        Validator.checkNull(model);

        PassInTripPer persistent = new PassInTripPer();
        persistent.setTrip(MOD_TO_PER_TRIP.getPersistentFrom(model.getTrip()));
        persistent.setPassenger(MOD_TO_PER_PASSENGER.getPersistentFrom(model.getPassenger()));
        persistent.setPlace(model.getPlace());
        persistent.setTime(model.getTime());
        return persistent;
    }
}