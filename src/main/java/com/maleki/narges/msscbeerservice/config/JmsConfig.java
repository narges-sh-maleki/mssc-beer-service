package com.maleki.narges.msscbeerservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Configuration
public class JmsConfig {

    public static final String BREWING_REQUEST_QUEUE = "brewing-request";
    public static final String NEW_INVENTORY_QUEUE = "new-inventory-request";
    public static final String VALIDATE_ORDER_RESULT = "validate-order-result";
    public final static String VALIDATE_ORDER = "validate-order";


  /* @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }*/

    @Bean
    public MessageConverter messageConverter(ObjectMapper objectMapper){

        MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
        messageConverter.setTargetType(MessageType.TEXT);

       /* HashMap<String, Class<?>> typeIdMappings = new HashMap<>();
        typeIdMappings.put(NewInventoryEvent.class.getSimpleName(), NewInventoryEvent.class);
        messageConverter.setTypeIdMappings(typeIdMappings);*/

        messageConverter.setTypeIdPropertyName("_type");
        messageConverter.setObjectMapper(objectMapper);
        return messageConverter;
    }
}
