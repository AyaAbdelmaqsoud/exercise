package com.jumia.exercise.dataaccess;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import com.jumia.exercise.domain.Customer;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@ActiveProfiles("h2-test")
@DataJpaTest
@Sql(scripts = "/delete-from-db.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Sql(scripts = "/insert-into-db.sql")
class CustomerRepositoryTest {

	@Autowired
	private CustomerRepository customerRepository;

	@Test
	void testFindByCountryCode() {

		List<Customer> customers = customerRepository.findByCountryCode("258");
		assertEquals(2, customers.size());

	}

}
