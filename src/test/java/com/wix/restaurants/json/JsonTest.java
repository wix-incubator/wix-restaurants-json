package com.wix.restaurants.json;

import static org.junit.Assert.*;

import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.LinkedList;

import com.fasterxml.jackson.core.type.TypeReference;

import org.junit.Test;

public class JsonTest {
	private static class ComplexObject {
		public String string;
		public Integer integer;
		public ComplexObject object;
	}
	
	@Test
	public void testComplexObjectJson() throws Exception {
		final ComplexObject obj = new ComplexObject();
		obj.string = "123";
		obj.integer = 123;
		obj.object = new ComplexObject();
		obj.object.string = "456";
		obj.object.integer = 456;
		
		final String json = Json.stringify(obj);
		final ComplexObject newObj = Json.parse(json, new TypeReference<ComplexObject>() {});
		
		assertEquals(obj.string, newObj.string);
		assertEquals(obj.integer, newObj.integer);
		assertEquals(obj.object.string, newObj.object.string);
		assertEquals(obj.object.integer, newObj.object.integer);
		assertEquals(obj.object.object, newObj.object.object);
	}
}
