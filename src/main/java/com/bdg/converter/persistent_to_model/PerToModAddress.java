package com.bdg.converter.persistent_to_model;

import com.bdg.converter.persistent_to_model.common.PerToMod;
import com.bdg.model.AddressMod;
import com.bdg.persistent.AddressPer;
import com.bdg.validator.Validator;

public class PerToModAddress extends PerToMod<AddressPer, AddressMod> {

    @Override
    public AddressMod getModelFrom(AddressPer persistent) {
        Validator.checkNull(persistent);

        AddressMod model = new AddressMod();
        model.setId(persistent.getId());
        model.setCountry(persistent.getCountry());
        model.setCity(persistent.getCity());

        return model;
    }
}