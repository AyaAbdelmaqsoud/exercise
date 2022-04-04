package com.jumia.exercise.restapi;

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

import com.jumia.exercise.model.CountryEnum;
import com.jumia.exercise.model.PhoneWrapper;
import com.jumia.exercise.phone.server.dto.PhoneApiDto;
import com.jumia.exercise.service.PhoneNumberService;

@ExtendWith(SpringExtension.class)
public class PhoneNumberRestImplTest {

	@Mock
	private PhoneNumberService phoneNumberService;

	@Mock
	private PhoneNumberMapper mapper;

	@InjectMocks
	private PhoneNumberRestImpl phoneNumberRestImpl;

	@Test
	public void testGetPhoneNumbers() {
		when(phoneNumberService.findCustomerPhoneNumbers()).thenReturn(createPhoneWrapperList());
		when(mapper.mapPhoneWrapperListToPhoneApiDtoList(any())).thenReturn(createPhoneApiDtoList());
		List<PhoneApiDto> phoneApiDtoList = phoneNumberRestImpl.getPhoneNumbers("", "", null, null);

		assertEquals(2, phoneApiDtoList.size());

	}

	@Test
	public void testGetPhoneNumbersByCountry() {
		List<PhoneWrapper> phoneWrappers = new ArrayList<>();
		PhoneWrapper wrapper1 = new PhoneWrapper(CountryEnum.CAMEROON, "237", "999999999", "VALID", "Aya");
		phoneWrappers.add(wrapper1);

		List<PhoneApiDto> phoneApiDtoList = new ArrayList<>();
		PhoneApiDto dto1 = new PhoneApiDto();
		dto1.setContact("Aya");
		dto1.setCountry("CAMEROON");
		dto1.setCountryCode("237");
		dto1.setState("VALID");
		dto1.setPhoneNumber("999999999");

		phoneApiDtoList.add(dto1);

		when(phoneNumberService.findCustomerPhoneNumbersByCountryOrState(any(), any())).thenReturn(phoneWrappers);
		when(mapper.mapPhoneWrapperListToPhoneApiDtoList(any())).thenReturn(phoneApiDtoList);
		List<PhoneApiDto> phoneApiDtoResultList = phoneNumberRestImpl.getPhoneNumbers("", "", "237", null);

		assertEquals(1, phoneApiDtoResultList.size());

	}

	@Test
	public void testGetPhoneNumbersByState() {
		when(phoneNumberService.findCustomerPhoneNumbersByCountryOrState(any(), any()))
				.thenReturn(createPhoneWrapperList());
		when(mapper.mapPhoneWrapperListToPhoneApiDtoList(any())).thenReturn(createPhoneApiDtoList());
		List<PhoneApiDto> phoneApiDtoList = phoneNumberRestImpl.getPhoneNumbers("", "", null, "Valid");

		assertEquals(2, phoneApiDtoList.size());

	}

	public List<PhoneWrapper> createPhoneWrapperList() {
		List<PhoneWrapper> phoneWrappers = new ArrayList<>();
		PhoneWrapper wrapper1 = new PhoneWrapper(CountryEnum.CAMEROON, "237", "999999999", "VALID", "Aya");
		PhoneWrapper wrapper2 = new PhoneWrapper(CountryEnum.ETHIOPIA, "251", "999999999", "VALID", "Aya");
		phoneWrappers.add(wrapper1);
		phoneWrappers.add(wrapper2);
		return phoneWrappers;
	}

	public List<PhoneApiDto> createPhoneApiDtoList() {
		List<PhoneApiDto> phoneApiDtoList = new ArrayList<>();
		PhoneApiDto dto1 = new PhoneApiDto();
		dto1.setContact("Aya");
		dto1.setCountry("CAMEROON");
		dto1.setCountryCode("237");
		dto1.setState("VALID");
		dto1.setPhoneNumber("999999999");

		PhoneApiDto dto2 = new PhoneApiDto();
		dto2.setContact("Aya");
		dto2.setCountry("ETHIOPIA");
		dto2.setCountryCode("251");
		dto2.setState("VALID");
		dto2.setPhoneNumber("999999999");

		phoneApiDtoList.add(dto1);
		phoneApiDtoList.add(dto2);
		return phoneApiDtoList;
	}
}
