package com.bdg.converter.model_to_persistent;

import com.bdg.model.PassengerMod;
import com.bdg.persistent.PassengerPer;
import com.bdg.validator.Validator;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

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


    @Override
    public Collection<PassengerPer> getPersistentListFrom(Collection<PassengerMod> modelList) {
        Validator.checkNull(modelList);

        Set<PassengerPer> passengerPerSet = new LinkedHashSet<>(modelList.size());
        for (PassengerMod tempPassengerMod : modelList) {
            passengerPerSet.add(getPersistentFrom(tempPassengerMod));
        }
        return passengerPerSet;
    }
}