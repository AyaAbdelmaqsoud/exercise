package com.jumia.exercise.dataaccess;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jumia.exercise.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	@Query("select cust from Customer cust where ((:countryCode is null) or (cust.phone like CONCAT('%(',:countryCode,')%')))")
	List<Customer> findByCountryCode(@Param("countryCode") String countryCode);

}
