package com.jumia.exercise.service;

import java.util.List;

import com.jumia.exercise.model.PhoneWrapper;

public interface PhoneNumberService {

	public List<PhoneWrapper> findCustomerPhoneNumbers();

	public List<PhoneWrapper> findCustomerPhoneNumbersByCountryOrState(String country, String state);

}
