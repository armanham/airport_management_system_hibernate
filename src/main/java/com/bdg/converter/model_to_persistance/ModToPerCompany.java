package com.bdg.converter.model_to_persistance;

import com.bdg.model.CompanyMod;
import com.bdg.persistent.CompanyPer;
import com.bdg.validator.Validator;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

public class ModToPerCompany extends ModToPer<CompanyMod, CompanyPer> {

    @Override
    public CompanyPer getPersistentFrom(CompanyMod model) {
        Validator.checkNull(model);

        CompanyPer persistent = new CompanyPer();
        persistent.setName(model.getName());
        persistent.setFoundDate(model.getFoundDate());
        return persistent;
    }


    @Override
    public Collection<CompanyPer> getPersistentListFrom(Collection<CompanyMod> modelList) {
        Validator.checkNull(modelList);

        Set<CompanyPer> companyPerSet = new LinkedHashSet<>(modelList.size());
        for (CompanyMod temCompanyMod : modelList) {
            companyPerSet.add(getPersistentFrom(temCompanyMod));
        }
        return companyPerSet;
    }
}