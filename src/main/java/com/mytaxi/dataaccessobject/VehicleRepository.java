package com.mytaxi.dataaccessobject;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mytaxi.domainobject.VehicleDO;
@Repository
public interface VehicleRepository extends CrudRepository<VehicleDO, Long>{

}
