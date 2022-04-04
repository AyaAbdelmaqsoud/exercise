package com.jumia.exercise.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jumia.exercise.common.Constants;

public class Morocco extends Country {

	@Override
	public boolean validatePhoneNumberFormat(String phoneNumber) {
		Pattern pattern = Pattern.compile(Constants.MoroccoRegex);
		Matcher matcher = pattern.matcher(phoneNumber);
		return matcher.matches();
	}
}
