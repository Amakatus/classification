package app.models.datas;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataDeltasTest {
    DataDeltas dataDeltas;

    public DataDeltasTest() {
        dataDeltas = new DataDeltas(1.2, 2.3);
    }

    @Test
    void should_get_min_of_delta() {
        assertEquals(1.2, dataDeltas.getMin());
    }

    @Test
    void should_get_max_of_delta() {
        assertEquals(2.3, dataDeltas.getMax());
    }

    @Test
    void should_get_delta() {
        assertEquals(2.3 - 1.2, dataDeltas.getDelta());
    }

    @Test
    void should_normalize_good_value() {
        assertEquals(0.5000000000000001, dataDeltas.getNormalizedValue(1.75));
    }

    @Test
    void should_normalize_good_too_high_value() {
        assertEquals(1, dataDeltas.getNormalizedValue(3));
    }

    @Test
    void should_normalize_good_too_low_value() {
        assertEquals(0, dataDeltas.getNormalizedValue(1.1));
    }

    @Test
    void should_denormalize_good_value() {
        assertEquals(2, dataDeltas.getUnormalizedValue(dataDeltas.getNormalizedValue(2)));
    }
}