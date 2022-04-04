package com.jumia.exercise.model;

import java.util.Arrays;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum CountryEnum {

	CAMEROON("237"), ETHIOPIA("251"), MOROCCO("212"), MOZAMBIQUE("258"), UGANDA("256");

	private String countryCode;

	public static Optional<CountryEnum> get(String code) {
		return Arrays.stream(CountryEnum.values()).filter(country -> country.countryCode.equals(code)).findFirst();
	}

}
