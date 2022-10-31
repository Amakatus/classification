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
	
	@Test
	void should_get_passenger_name() {
		TitanicPassengerData passenger = new TitanicPassengerData();
		passenger.setName("Antoine");
		assertEquals("Antoine",passenger.getName());
	}
	
	@Test
	void should_get_passenger_sex() {
		TitanicPassengerData passenger = new TitanicPassengerData();
		passenger.setMale(true);
		assertTrue(passenger.isMale());
	}
	
	@Test
	void should_get_passenger_age() {
		TitanicPassengerData passenger = new TitanicPassengerData();
		passenger.setAge(34);
		assertEquals(34,passenger.getAge());
	}
	
	@Test
	void should_get_passenger_sibsp() {
		TitanicPassengerData passenger = new TitanicPassengerData();
		passenger.setSibSp(2);
		assertEquals(2,passenger.getSibSp());
	}
	
	@Test
	void should_get_passenger_parch() {
		TitanicPassengerData passenger = new TitanicPassengerData();
		passenger.setParch(4);
		assertEquals(4,passenger.getParch());
	}
	
	
}
