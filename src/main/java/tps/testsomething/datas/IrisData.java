package tps.testsomething.datas;

public class IrisData extends UsableData {
	private String name;
	
	@DistanceField
	private int size;
	
	@DistanceField
	private String color;
	
	public IrisData(String name, int size, String color) {
		this.name = name;
		this.size = size;
		this.color = color;
	}
}
