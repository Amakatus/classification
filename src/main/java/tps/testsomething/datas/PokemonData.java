package tps.testsomething.datas;

public class PokemonData extends UsableData {
	
	private String name;
	
	@DistanceField
	private int powerAttack;
	
	@DistanceField
	private int defenseAttack;
	
	@DistanceField
	private int healthPoints;
	
	private boolean legendary;
	
	public PokemonData(String name, int pa, int da, int hp, boolean legendary) {
		this.name = name;
		this.powerAttack = pa;
		this.defenseAttack = da;
		this.healthPoints = hp;
		this.legendary = legendary;
	}
}
