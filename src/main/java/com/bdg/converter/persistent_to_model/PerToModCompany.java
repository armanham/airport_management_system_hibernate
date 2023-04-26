package com.bdg.converter.persistent_to_model;

import com.bdg.model.CompanyMod;
import com.bdg.persistent.CompanyPer;

import java.util.Collection;

public class PerToModCompany extends PerToMod<CompanyPer, CompanyMod> {

    @Override
    public CompanyMod getModelFrom(CompanyPer persistent) {
        if (persistent == null) {
            throw new NullPointerException("Passed null value as 'persistent': ");
        }

        CompanyMod model = new CompanyMod();
        model.setId(persistent.getId());
        model.setName(persistent.getName());
        model.setFoundDate(persistent.getFoundDate());
        return model;
    }

    //TODO Add implementation
    @Override
    public Collection<CompanyMod> getModelListFrom(Collection<CompanyPer> persistentList) {
        return null;
    }
}