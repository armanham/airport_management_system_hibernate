package com.bdg.converter.model_to_persistent;

import com.bdg.converter.model_to_persistent.common.ModToPer;
import com.bdg.model.PassengerMod;
import com.bdg.persistent.PassengerPer;
import com.bdg.validator.Validator;

public class ModToPerPassenger extends ModToPer<PassengerMod, PassengerPer> {

    private final static ModToPerAddress MOD_TO_PER_ADDRESS = new ModToPerAddress();

    @Override
    public PassengerPer getPersistentFrom(PassengerMod model) {
        Validator.checkNull(model);

        PassengerPer persistent = new PassengerPer();
        persistent.setName(model.getName());
        persistent.setPhone(model.getPhone());
        persistent.setAddress(MOD_TO_PER_ADDRESS.getPersistentFrom(model.getAddress()));
        return persistent;
    }
}