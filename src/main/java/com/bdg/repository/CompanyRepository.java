package com.bdg.repository;

import com.bdg.model.CompanyMod;
import com.bdg.repository.common.CommonRepository;

public interface CompanyRepository extends CommonRepository<CompanyMod> {

    boolean updateBy(int id, CompanyMod item);
}