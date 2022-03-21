package javacson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.Test;
import static javacson.CsonCodepoints.CSON_SCHEMA_DATA_SEP;
import static javacson.CsonCodepoints.OBJECT_VAL;
import static javacson.CsonCodepoints.FIELD_VAL;

public class StudentTest {
    public class A {
        public int afield = 0;
    }

    public class B {
        public int bfield = 1;
    }

    // the types and fields in the schema appear in lexographic order but the
    // objects in the data section appear sequentially
    @Test
    public void typesInLexographic() {
        // TODO
        CsonObjectSerializer serialzer = new CsonObjectSerializer();
        var result = serialzer.serialize(List.of(
                new B(),
                new A()));
        assertEquals(
                "🍽A🥣afield🧂int32"
                        + "🍽B🥣bfield🧂int32"
                        + CSON_SCHEMA_DATA_SEP
                        + OBJECT_VAL + "B" + FIELD_VAL + "1"
                        + OBJECT_VAL + "A" + FIELD_VAL + "0",
                result);
    }

    // this will just test a if a singular object is passed in
    @Test
    public void passSingularObject() {
        CsonObjectSerializer serialzer = new CsonObjectSerializer();
        var result = serialzer.serialize(new B());
        assertEquals("🍽B🥣bfield🧂int32" 
                    + CSON_SCHEMA_DATA_SEP + OBJECT_VAL + "B" + FIELD_VAL + "1", result);

    }
}
