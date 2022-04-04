package com.jumia.exercise.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jumia.exercise.dataaccess.CustomerRepository;
import com.jumia.exercise.domain.Customer;
import com.jumia.exercise.model.Cameroon;
import com.jumia.exercise.model.Country;
import com.jumia.exercise.model.CountryEnum;
import com.jumia.exercise.model.Ethiopia;
import com.jumia.exercise.model.Morocco;
import com.jumia.exercise.model.Mozambique;
import com.jumia.exercise.model.PhoneWrapper;
import com.jumia.exercise.model.StateEnum;
import com.jumia.exercise.model.Uganda;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PhoneNumberServiceImpl implements PhoneNumberService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public List<PhoneWrapper> findCustomerPhoneNumbers() {
		List<Customer> customerList = customerRepository.findAll();
		return filterCustomerList(customerList);
	}

	@Override
	public List<PhoneWrapper> findCustomerPhoneNumbersByCountryOrState(String country, String state) {
		List<Customer> customerList = customerRepository.findByCountryCode(country);
		List<PhoneWrapper> phoneWrappers = filterCustomerList(customerList);
		if (StringUtils.hasText(state)) {
			return phoneWrappers.stream().filter(c -> c.getState().compareTo(state) == 0).collect(Collectors.toList());
		}
		return phoneWrappers;
	}

	private List<PhoneWrapper> filterCustomerList(List<Customer> customerList) {
		List<PhoneWrapper> phoneWrappers = new ArrayList<>();

		for (Customer customer : customerList) {
			String countryCode = customer.getPhone().substring(customer.getPhone().indexOf("(") + 1,
					customer.getPhone().indexOf(")"));
			Optional<CountryEnum> country = CountryEnum.get(countryCode);
			CountryEnum countryValue = null;
			if (country.isPresent()) {
				countryValue = country.get();
			}
			String phone = customer.getPhone()
					.substring(customer.getPhone().indexOf(")") + 1, customer.getPhone().length()).trim();
			String state = determineStateByPhoneAndCountry(countryValue, customer.getPhone());

			PhoneWrapper wrapper = new PhoneWrapper(country.get(), countryCode, phone, state, customer.getName());

			phoneWrappers.add(wrapper);
		}
		return phoneWrappers;
	}

	private String determineStateByPhoneAndCountry(CountryEnum country, String phone) {
		boolean state;
		switch (country) {
		case CAMEROON:
			Country cameroon = new Cameroon();
			state = cameroon.validatePhoneNumberFormat(phone);
			break;
		case ETHIOPIA:
			Country ethiopia = new Ethiopia();
			state = ethiopia.validatePhoneNumberFormat(phone);
			break;
		case MOROCCO:
			Country morocco = new Morocco();
			state = morocco.validatePhoneNumberFormat(phone);
			break;
		case MOZAMBIQUE:
			Country mozambique = new Mozambique();
			state = mozambique.validatePhoneNumberFormat(phone);
			break;
		case UGANDA:
			Country uganda = new Uganda();
			state = uganda.validatePhoneNumberFormat(phone);
		default:
			state = false;
		}
		if (StateEnum.get(state).isPresent()) {
			return StateEnum.get(state).get().toString();
		}
		return null;
	}

}