package com.bdg.converter.persistent_to_model;

import com.bdg.model.CompanyMod;
import com.bdg.persistent.CompanyPer;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

public class PerToModCompany extends PerToMod<CompanyPer, CompanyMod> {

    @Override
    public CompanyMod getModelFrom(CompanyPer persistent) {
        checkNull(persistent);

        CompanyMod model = new CompanyMod();
        model.setId(persistent.getId());
        model.setName(persistent.getName());
        model.setFoundDate(persistent.getFoundDate());
        return model;
    }


    @Override
    public Collection<CompanyMod> getModelListFrom(Collection<CompanyPer> persistentList) {
        checkNull(persistentList);

        Set<CompanyMod> companyModSet = new LinkedHashSet<>(persistentList.size());
        for (CompanyPer tempCompanyPer : persistentList) {
            companyModSet.add(getModelFrom(tempCompanyPer));
        }
        return companyModSet;
    }


    private static void checkNull(Object item) {
        if (item == null) {
            throw new NullPointerException("Passed null value as 'item': ");
        }
    }
}