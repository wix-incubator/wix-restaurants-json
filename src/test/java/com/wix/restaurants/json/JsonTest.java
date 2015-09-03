package com.wix.restaurants.json;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Test;
import scala.Option;

import static org.junit.Assert.assertEquals;

public class JsonTest {
	private static class ComplexJavaObject {
		public String string;
		public Integer integer;
		public ComplexJavaObject object;
	}

    private static class ComplexJavaObjectWithExtraField extends ComplexJavaObject {
        public String extraString;
    }

	private static class ComplexScalaObject {
		public String string;
		public Integer integer;
		public Option<ComplexScalaObject> object;
	}

	@Test
	public void testComplexJavaObjectJson() throws Exception {
		final ComplexJavaObject obj = new ComplexJavaObject();
		obj.string = "123";
		obj.integer = 123;
		obj.object = new ComplexJavaObject();
		obj.object.string = "456";
		obj.object.integer = 456;
		
		final String json = Json.stringify(obj);
		final ComplexJavaObject newObj = Json.parse(json, new TypeReference<ComplexJavaObject>() {});
		
		assertEquals(obj.string, newObj.string);
		assertEquals(obj.integer, newObj.integer);
		assertEquals(obj.object.string, newObj.object.string);
		assertEquals(obj.object.integer, newObj.object.integer);
		assertEquals(obj.object.object, newObj.object.object);
	}

    @Test
    public void testComplexJavaObjectJsonWithExtraFields() throws Exception {
        final ComplexJavaObjectWithExtraField obj = new ComplexJavaObjectWithExtraField();
        obj.extraString = "extra string";

        final String json = Json.stringify(obj);

        // This shouldn't throw
        Json.parse(json, new TypeReference<ComplexJavaObject>() {});
    }

    @Test
    public void testComplexScalaObjectJson() throws Exception {
        final ComplexScalaObject obj = new ComplexScalaObject();
        obj.string = "123";
        obj.integer = 123;
        obj.object = Option.apply(new ComplexScalaObject());
        obj.object.get().string = "456";
        obj.object.get().integer = 456;
        obj.object.get().object = Option.apply(null);

        final String json = Json.stringify(obj);
        final ComplexScalaObject newObj = Json.parse(json, new TypeReference<ComplexScalaObject>() {});

        assertEquals(obj.string, newObj.string);
        assertEquals(obj.integer, newObj.integer);
        assertEquals(obj.object.get().string, newObj.object.get().string);
        assertEquals(obj.object.get().integer, newObj.object.get().integer);
        assertEquals(obj.object.get().object, newObj.object.get().object);
    }
}
