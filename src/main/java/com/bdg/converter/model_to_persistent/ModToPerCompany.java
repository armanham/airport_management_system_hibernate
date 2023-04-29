package com.bdg.converter.model_to_persistent;

import com.bdg.model.CompanyMod;
import com.bdg.persistent.CompanyPer;
import com.bdg.validator.Validator;

public class ModToPerCompany extends ModToPer<CompanyMod, CompanyPer> {

    @Override
    public CompanyPer getPersistentFrom(CompanyMod model) {
        Validator.checkNull(model);

        CompanyPer persistent = new CompanyPer();
        persistent.setName(model.getName());
        persistent.setFoundDate(model.getFoundDate());
        return persistent;
    }
}