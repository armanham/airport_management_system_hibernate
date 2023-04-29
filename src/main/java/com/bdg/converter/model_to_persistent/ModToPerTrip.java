package com.bdg.converter.model_to_persistent;

import com.bdg.converter.model_to_persistent.common.ModToPer;
import com.bdg.model.TripMod;
import com.bdg.persistent.TripPer;
import com.bdg.validator.Validator;

public class ModToPerTrip extends ModToPer<TripMod, TripPer> {

    private static final ModToPerCompany MOD_TO_PER_COMPANY = new ModToPerCompany();


    @Override
    public TripPer getPersistentFrom(TripMod model) {
        Validator.checkNull(model);

        TripPer persistent = new TripPer();
        persistent.setCompany(MOD_TO_PER_COMPANY.getPersistentFrom(model.getCompany()));
        persistent.setAirplane(model.getAirplane());
        persistent.setTownFrom(model.getTownFrom());
        persistent.setTownTo(model.getTownTo());
        persistent.setTimeIn(model.getTimeIn());
        persistent.setTimeOut(model.getTimeOut());

        return persistent;
    }
}
