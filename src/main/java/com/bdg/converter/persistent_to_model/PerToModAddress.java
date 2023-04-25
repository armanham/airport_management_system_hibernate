package com.bdg.converter.persistent_to_model;

import com.bdg.model.AddressMod;
import com.bdg.persistent.AddressPer;

public class PerToModAddress extends PerToMod<AddressPer, AddressMod> {

    @Override
    public AddressMod getModelFromPersistent(AddressPer persistent) {
        if (persistent == null) {
            throw new NullPointerException("Passed null value as 'persistent': ");
        }

        AddressMod model = new AddressMod();
        model.setId(persistent.getId());
        model.setCountry(persistent.getCountry());
        model.setCity(persistent.getCity());
        return model;
    }
}
