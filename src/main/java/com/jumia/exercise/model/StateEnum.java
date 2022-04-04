package com.jumia.exercise.model;

import java.util.Arrays;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum StateEnum {

	VALID(Boolean.TRUE), INVALID(Boolean.FALSE);

	private Boolean state;

	public static Optional<StateEnum> get(Boolean value) {
		return Arrays.stream(StateEnum.values()).filter(state -> state.state.equals(value)).findFirst();
	}
}
