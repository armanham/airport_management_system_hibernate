package com.bdg.converter.model_to_persistent;

import com.bdg.converter.model_to_persistent.common.ModToPer;
import com.bdg.model.AddressMod;
import com.bdg.persistent.AddressPer;
import com.bdg.validator.Validator;

public class ModToPerAddress extends ModToPer<AddressMod, AddressPer> {

    @Override
    public AddressPer getPersistentFrom(AddressMod model) {
        Validator.checkNull(model);

        AddressPer persistent = new AddressPer();
        persistent.setCountry(model.getCountry());
        persistent.setCity(model.getCity());

        return persistent;
    }
}