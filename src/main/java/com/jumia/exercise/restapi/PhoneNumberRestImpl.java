package com.jumia.exercise.restapi;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.apache.cxf.feature.Features;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.jumia.exercise.model.PhoneWrapper;
import com.jumia.exercise.phone.server.api.PhoneApi;
import com.jumia.exercise.phone.server.dto.PhoneApiDto;
import com.jumia.exercise.service.PhoneNumberService;

import lombok.extern.slf4j.Slf4j;

@Component
@Features(features = { "org.apache.cxf.jaxrs.validation.JAXRSBeanValidationFeature",
		"org.apache.cxf.ext.logging.LoggingFeature" })
@Slf4j
public class PhoneNumberRestImpl implements PhoneApi {

	@Autowired
	private PhoneNumberService phoneNumberService;

	@Autowired
	private PhoneNumberMapper mapper;

	@Override
	public List<PhoneApiDto> getPhoneNumbers(@NotNull String xCMPTenantId, @NotNull String xCorrelationId,
			String country, String state) {
		List<PhoneWrapper> phoneWrapperList = null;
		if (StringUtils.hasText(country) || StringUtils.hasText(state)) {
			log.info("find Customer Phone Numbers By Country Or State");
			phoneWrapperList = phoneNumberService.findCustomerPhoneNumbersByCountryOrState(country, state);
		} else {
			log.info("find all Customer Phone Numbers");
			phoneWrapperList = phoneNumberService.findCustomerPhoneNumbers();
		}
		return mapper.mapPhoneWrapperListToPhoneApiDtoList(phoneWrapperList);
	}

}
