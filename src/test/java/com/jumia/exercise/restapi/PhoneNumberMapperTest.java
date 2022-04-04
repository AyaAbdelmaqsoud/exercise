package com.jumia.exercise.restapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.jumia.exercise.model.CountryEnum;
import com.jumia.exercise.model.PhoneWrapper;
import com.jumia.exercise.phone.server.dto.PhoneApiDto;

@ExtendWith(SpringExtension.class)
public class PhoneNumberMapperTest {

	@InjectMocks
	private PhoneNumberMapperImpl mapper;

	@Test
	public void testMapPhoneWrapperListToPhoneApiDtoList() {
		List<PhoneWrapper> phoneWrapperList = createPhoneWrapperList();
		List<PhoneApiDto> phoneApiDtoList = mapper.mapPhoneWrapperListToPhoneApiDtoList(phoneWrapperList);
		assertNotNull(phoneApiDtoList);
		assertEquals(phoneApiDtoList.size(), 3);

	}

	private List<PhoneWrapper> createPhoneWrapperList() {
		List<PhoneWrapper> phoneWrappers = new ArrayList<>();
		PhoneWrapper wrapper1 = new PhoneWrapper(CountryEnum.CAMEROON, "237", "999999999", "VALID", "Aya");
		PhoneWrapper wrapper2 = new PhoneWrapper(CountryEnum.ETHIOPIA, "251", "999999999", "VALID", "Aya");
		PhoneWrapper wrapper3 = new PhoneWrapper(CountryEnum.UGANDA, "256", "999999999", "INVALID", "Aya");
		phoneWrappers.add(wrapper1);
		phoneWrappers.add(wrapper2);
		phoneWrappers.add(wrapper3);
		return phoneWrappers;

	}
}
