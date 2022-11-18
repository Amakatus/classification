package app.graphics.models.datas.points;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import app.graphics.models.datas.data.AbstractData;

class PointTest {

	@Test
	void Test_getter_and_setter_for_the_position_x_to_the_point() {
		Point<AbstractData> point = new Point<AbstractData>();
		point.setxPos(12.5);
		assertEquals(12.5, point.getxPos()); 
	}
	@Test
	void Test_getter_and_setter_for_the_position_y_to_the_point() {
		Point<AbstractData> point = new Point<AbstractData>();
		point.setyPos(20.5);
		assertEquals(20.5, point.getyPos()); 
	}
}
