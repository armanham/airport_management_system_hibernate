package com.bdg.ax2sap.airport_management_system.repository;

import com.bdg.ax2sap.airport_management_system.model.CompanyMod;
import com.bdg.ax2sap.airport_management_system.repository.common.CommonRepository;

public interface CompanyRepository extends CommonRepository<CompanyMod> {

    boolean updateBy(int id, CompanyMod item);
}