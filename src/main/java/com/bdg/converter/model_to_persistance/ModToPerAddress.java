package com.bdg.converter.model_to_persistance;

import com.bdg.model.AddressMod;
import com.bdg.persistent.AddressPer;
import com.bdg.validator.Validator;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

public class ModToPerAddress extends ModToPer<AddressMod, AddressPer> {

    @Override
    public AddressPer getPersistentFrom(AddressMod model) {
        Validator.checkNull(model);

        AddressPer persistent = new AddressPer();
        persistent.setCountry(model.getCountry());
        persistent.setCity(model.getCity());
        return persistent;
    }

    @Override
    public Collection<AddressPer> getPersistentListFrom(Collection<AddressMod> modelList) {
        Validator.checkNull(modelList);

        Set<AddressPer> addressesPerSet = new LinkedHashSet<>(modelList.size());
        for (AddressMod tempAddressMod : modelList) {
            addressesPerSet.add(getPersistentFrom(tempAddressMod));
        }
        return addressesPerSet;
    }
}