package app.graphics.models.datas.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TitanicPassengerDataTest {

	@Test
	void should_get_passenger_id() {
		TitanicPassengerData passenger = new TitanicPassengerData();
		passenger.setId(23);
		assertEquals(23,passenger.getId());
	}
	
}
