package com.sda.groupa.shippingcostcalculator.driver.driverRepository;

import com.sda.groupa.shippingcostcalculator.driver.driverModel.Driver;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface DriverRepository extends JpaRepository<Driver, Long> {


/*    @Query("select u from User u where u.lastName = :lastName")
    List<User> searchByLastName(@Param("lastName") String lastName);*/

    @Query("select d from Driver d where d.user.username = :user")
    Optional<Driver> findDriverByUser(String user);

}
