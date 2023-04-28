package com.bdg.repository;

import com.bdg.model.AddressMod;
import com.bdg.repository.common.CommonRepository;

public interface AddressRepository extends CommonRepository<AddressMod> {

    int getId(AddressMod addressMod);

    boolean exists(AddressMod addressMod);

    boolean updateBy(int id, String newCountry, String newCity);
}