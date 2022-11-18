package app.graphics.models.datas.points;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import app.graphics.models.datas.data.Data;

class PointTest {

	@Test
	void Test_getter_and_setter_for_the_position_x_to_the_point() {
		Point<Data> point = new Point<Data>();
		point.setxPos(12.5);
		assertEquals(12.5, point.getxPos()); 
	}

}
