package com.jumia.exercise.restapi;

import java.util.List;

import org.mapstruct.Mapper;

import com.jumia.exercise.model.PhoneWrapper;
import com.jumia.exercise.phone.server.dto.PhoneApiDto;

@Mapper(componentModel = "spring")
public abstract class PhoneNumberMapper {

	public abstract List<PhoneApiDto> mapPhoneWrapperListToPhoneApiDtoList(List<PhoneWrapper> phoneWrapperList);

}
