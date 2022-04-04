package com.jumia.exercise.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jumia.exercise.common.Constants;

public class Mozambique extends Country {

	@Override
	public boolean validatePhoneNumberFormat(String phoneNumber) {
		Pattern pattern = Pattern.compile(Constants.MozambiqueRegex);
		Matcher matcher = pattern.matcher(phoneNumber);
		return matcher.matches();
	}

}
