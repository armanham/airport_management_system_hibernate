package com.bdg.converter.persistent_to_model;

import com.bdg.converter.persistent_to_model.common.PerToMod;
import com.bdg.model.TripMod;
import com.bdg.persistent.TripPer;
import com.bdg.validator.Validator;

public class PerToModTrip extends PerToMod<TripPer, TripMod> {

    private static final PerToModCompany PER_TO_MOD_COMPANY = new PerToModCompany();

    @Override
    public TripMod getModelFrom(TripPer persistent) {
        Validator.checkNull(persistent);

        TripMod model = new TripMod();
        model.setId(persistent.getTripNumber());
        model.setCompany(PER_TO_MOD_COMPANY.getModelFrom(persistent.getCompany()));
        model.setAirplane(persistent.getAirplane());
        model.setTownFrom(persistent.getTownFrom());
        model.setTownTo(persistent.getTownTo());
        model.setTimeIn(persistent.getTimeIn());
        model.setTimeOut(persistent.getTimeOut());

        return model;
    }
}