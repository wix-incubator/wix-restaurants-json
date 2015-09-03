package com.wix.restaurants.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.scala.DefaultScalaModule;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Json {
	public static final ObjectMapper mapper = new ObjectMapper();
    static {
        mapper.registerModule(new DefaultScalaModule());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
	
	public static <T> T parse(byte[] json, TypeReference<T> typeReference) {
		try {
			return mapper.readValue(json, typeReference);
		} catch (IOException e) {
			LoggerFactory.getLogger(Json.class).error("JSON deserialization error.", e);
			throw new RuntimeException(e);
		}
	}
	
	public static <T> T parse(byte[] json, Class<T> valueType) {
		try {
			return mapper.readValue(json, valueType);
		} catch (IOException e) {
			LoggerFactory.getLogger(Json.class).error("JSON deserialization error.", e);
			throw new RuntimeException(e);
		}
	}	
	
	public static <T> T parse(String json, TypeReference<T> typeReference) {
		try {
			return mapper.readValue(json, typeReference);
		} catch (IOException e) {
			LoggerFactory.getLogger(Json.class).error("JSON deserialization error.", e);
			throw new RuntimeException(e);
		}
	}
	
	public static <T> T parse(String json, Class<T> valueType) {
		try {
			return mapper.readValue(json, valueType);
		} catch (IOException e) {
			LoggerFactory.getLogger(Json.class).error("JSON deserialization error.", e);
			throw new RuntimeException(e);
		}
	}
	
	public static byte[] bytify(Object obj) {
		try {
			return mapper.writeValueAsBytes(obj);
		} catch (IOException e) {
			LoggerFactory.getLogger(Json.class).error("JSON serialization error.", e);
			throw new RuntimeException(e);
		}
	}
	
	public static String stringify(Object obj) {
		try {
			return mapper.writeValueAsString(obj);
		} catch (IOException e) {
			LoggerFactory.getLogger(Json.class).error("JSON serialization error.", e);
			throw new RuntimeException(e);
		}
	}
}
