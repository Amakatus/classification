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
	
	@Test
	void should_know_if_passenger_survived_or_not() {
		TitanicPassengerData passenger = new TitanicPassengerData();
		passenger.setSurvived(true);
		assertTrue(passenger.isSurvived());
	}
	
	@Test
	void should_get_passenger_pclass() {
		TitanicPassengerData passenger = new TitanicPassengerData();
		passenger.setpClass(3);
		assertEquals(3,passenger.getpClass());
	}
}
