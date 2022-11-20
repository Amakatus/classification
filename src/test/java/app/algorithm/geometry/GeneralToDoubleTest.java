package app.algorithm.geometry;

import app.graphics.models.datas.data.IrisVariety;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GeneralToDoubleTest {

    @Test
    void test_boolean_to_double() {
        assertEquals(0, GeneralToDouble.toDouble(true, true));
        assertEquals(0, GeneralToDouble.toDouble(false, false));
        assertEquals(1, GeneralToDouble.toDouble(false, true));
        assertEquals(1, GeneralToDouble.toDouble(true, false));
    }

    @Test
    void test_string_to_double() {
        assertEquals(0, GeneralToDouble.toDouble("a", "a"));
        assertEquals(0, GeneralToDouble.toDouble("b", "b"));
        assertEquals(0, GeneralToDouble.toDouble("a", "A"));
        assertEquals(1, GeneralToDouble.toDouble("a", "b"));
        assertEquals(1, GeneralToDouble.toDouble("b", "a"));
    }

    @Test
    void test_enum_to_double() {
        assertEquals(0, GeneralToDouble.toDouble(IrisVariety.SETOSA, IrisVariety.SETOSA));
        assertEquals(1, GeneralToDouble.toDouble(IrisVariety.SETOSA, IrisVariety.VERSICOLOR));
    }
}
