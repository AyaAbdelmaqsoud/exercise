package com.jumia.exercise.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PhoneWrapper {

	CountryEnum country;
	String countryCode;
	String phoneNumber;
	String state;
	String contact;
}
