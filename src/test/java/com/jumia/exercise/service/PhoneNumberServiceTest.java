package com.jumia.exercise.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.jumia.exercise.dataaccess.CustomerRepository;
import com.jumia.exercise.domain.Customer;
import com.jumia.exercise.model.PhoneWrapper;

@ExtendWith(SpringExtension.class)
public class PhoneNumberServiceTest {

	@Mock
	private CustomerRepository customerRepository;

	@InjectMocks
	private PhoneNumberServiceImpl phoneNumberService;

	@Test
	public void testFindCustomerPhoneNumbers() {
		when(customerRepository.findAll()).thenReturn(createCustomerList());
		List<PhoneWrapper> wrapperlist = phoneNumberService.findCustomerPhoneNumbers();
		assertEquals(3, wrapperlist.size());
	}

	@Test
	public void testFindCustomerPhoneNumbersByCountry() {
		List<Customer> customerList = new ArrayList<Customer>();
		Customer customer1 = new Customer(1, "Mahmoud", "(258) 84330678235");
		customerList.add(customer1);

		when(customerRepository.findByCountryCode(any())).thenReturn(customerList);
		List<PhoneWrapper> wrapperlist = phoneNumberService.findCustomerPhoneNumbersByCountryOrState("258", null);
		assertEquals(1, wrapperlist.size());
		assertEquals("INVALID", wrapperlist.get(0).getState());
	}

	@Test
	public void testFindCustomerPhoneNumbersByState() {
		List<Customer> customerList = new ArrayList<Customer>();
		Customer customer1 = new Customer(1, "Mahmoud", "(258) 84330678235");
		customerList.add(customer1);

		when(customerRepository.findByCountryCode(any())).thenReturn(createCustomerList());
		List<PhoneWrapper> wrapperlist = phoneNumberService.findCustomerPhoneNumbersByCountryOrState(null, "VALID");
		assertEquals(2, wrapperlist.size());
	}

	private List<Customer> createCustomerList() {
		List<Customer> customerList = new ArrayList<Customer>();
		Customer customer1 = new Customer(1, "Mahmoud", "(258) 84330678235");
		Customer customer2 = new Customer(1, "Ahmed", "(212) 691933626");
		Customer customer3 = new Customer(1, "Khaled", "(251) 914148181");
		customerList.add(customer1);
		customerList.add(customer2);
		customerList.add(customer3);
		return customerList;
	}

}