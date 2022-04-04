package com.jumia.exercise.config;

import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.jaxrs.provider.JAXBElementProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

@Configuration
public class AppConfig {

    /**
     * JAXB JSON Provider to support JSON generation
     */
    @Bean
    public JacksonJaxbJsonProvider jacksonJaxbJsonProvider() {
        JacksonJaxbJsonProvider provider = new JacksonJaxbJsonProvider();
        ObjectMapper objectMapper = this.objectMapper();
        provider.setMapper(objectMapper);
        return provider;
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        configureMapper(objectMapper);
        return objectMapper;
    }

    private void configureMapper(ObjectMapper objectMapper) {
        objectMapper.registerModule(new JaxbAnnotationModule());
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Bean
    public JAXBElementProvider<?> jaxbElementProvider() {
        JAXBElementProvider<?> elementProvider = new JAXBElementProvider<>();
        elementProvider.setMarshallAsJaxbElement(true);
        elementProvider.setUnmarshallAsJaxbElement(true);
        return elementProvider;
    }
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate rt = new RestTemplate();
        MappingJackson2HttpMessageConverter jsonMessageConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = this.objectMapper();
        jsonMessageConverter.setObjectMapper(objectMapper);
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(jsonMessageConverter);
        rt.setMessageConverters(messageConverters);
        rt.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        return rt;

    }

}
