package app.graphics.models.datas.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
		passenger.setSex("male");
		assertEquals("male", passenger.getSex());
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
	
	@Test
	void should_get_passenger_ticket() {
		TitanicPassengerData passenger = new TitanicPassengerData();
		passenger.setTicket("marseille");
		assertEquals("marseille",passenger.getTicket());
	}
	
	@Test
	void should_get_passenger_fare() {
		TitanicPassengerData passenger = new TitanicPassengerData();
		passenger.setFare(334);
		assertEquals(334,passenger.getFare());
	}
	
	@Test
	void should_get_passenger_cabin() {
		TitanicPassengerData passenger = new TitanicPassengerData();
		passenger.setCabin("cabine jacoco");
		assertEquals("cabine jacoco",passenger.getCabin());
	}
	
	@Test
	void should_get_passenger_embarked() {
		TitanicPassengerData passenger = new TitanicPassengerData();
		passenger.setEmbarked('I');
		assertEquals('I',passenger.getEmbarked());
	}
	
}
