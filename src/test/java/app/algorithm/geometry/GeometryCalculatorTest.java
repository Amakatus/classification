package app.algorithm.geometry;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import app.graphics.models.datas.data.IrisData;

class GeometryCalculatorTest {

	@Test
	void should_add_field_in_list() {
		List<String> fieldName = new ArrayList<>();
		EuclideanGeometry<IrisData> gc = new EuclideanGeometry<>(fieldName);
		gc.addField("iris1");
		assertEquals("iris1",gc.getFieldsNames().get(0));
		assertEquals(1,gc.getFieldsNames().size());
	}
	
	@Test
	void should_remove_field_in_list() {
		List<String> fieldName = new ArrayList<>();
		EuclideanGeometry<IrisData> gc = new EuclideanGeometry<>(fieldName);
		gc.addField("iris1");
		gc.removeFieldName("iris1");
		assertEquals(0,gc.getFieldsNames().size());
	}

}
