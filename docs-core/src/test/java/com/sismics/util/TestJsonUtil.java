package com.sismics.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.json.Json;
import javax.json.JsonValue;

import org.junit.Test;

public class TestJsonUtil {

    @Test
    public void testNullableString() {
        // Test with non-null string
        String stringValue = "test";
        JsonValue result = JsonUtil.nullable(stringValue);
        assertTrue(result.getValueType() == JsonValue.ValueType.STRING);
        assertEquals(stringValue, result.toString());

        // Test with null string
        String nullString = null;
        result = JsonUtil.nullable(nullString);
        assertTrue(result == JsonValue.NULL);
    }

    @Test
    public void testNullableInteger() {
        // Test with non-null integer
        Integer integerValue = 123;
        JsonValue result = JsonUtil.nullable(integerValue);
        assertTrue(result.getValueType() == JsonValue.ValueType.NUMBER);
        assertEquals((Object) integerValue, Long.parseLong(result.toString())); // Cast to Object to resolve ambiguity

        // Test with null integer
        Integer nullInteger = null;
        result = JsonUtil.nullable(nullInteger);
        assertTrue(result == JsonValue.NULL);
    }

    @Test
    public void testNullableLong() {
        // Test with non-null long
        Long longValue = 123L;
        JsonValue result = JsonUtil.nullable(longValue);
        assertTrue(result.getValueType() == JsonValue.ValueType.NUMBER);
        assertEquals((Object) longValue, Long.parseLong(result.toString())); // Cast to Object to resolve ambiguity

        // Test with null long
        Long nullLong = null;
        result = JsonUtil.nullable(nullLong);
        assertTrue(result == JsonValue.NULL);
    }
}
