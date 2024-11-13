package com.wanted.clone.oneport.core.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

public class RequestConvertTest {
    @Test
    public void testConvert() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.readValue("{\"name\":\"Bob\", \"age\":13}", MyValue.class);
    }
}
