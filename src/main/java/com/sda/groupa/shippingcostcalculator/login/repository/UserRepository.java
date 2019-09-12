package com.sda.groupa.shippingcostcalculator.login.repository;

import com.sda.groupa.shippingcostcalculator.login.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

}
