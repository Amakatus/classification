package tps.tpcsv.tppokemon;

import com.opencsv.bean.CsvBindByName;

public class Pokemon {
	@CsvBindByName(column = "name")
	private String name;
	
	@CsvBindByName(column = "attack")
	private int attackPoints;
	
	@CsvBindByName(column = "base_egg_steps")
	private int baseEggSteps;
	
	@CsvBindByName(column = "capture_rate")
	private double captureRate;
	
	@CsvBindByName(column = "defense")
	private int defensePoints;
	
	@CsvBindByName(column = "experience_growth")
	private int experienceGrowth;
	
	@CsvBindByName(column = "hp")
	private int healthPoints;
	
	@CsvBindByName(column = "sp_attack")
	private int spAttackPoints;
	
	@CsvBindByName(column = "sp_defense")
	private int spDefensePoints;
	
	@CsvBindByName(column = "type1")
	private String type1;
	
	@CsvBindByName(column = "type2")
	private String type2;
	
	@CsvBindByName(column = "speed")
	private double speed;
	
	@CsvBindByName(column = "is_legendary")
	private boolean legendary;

	public double getSpeed() { return this.speed; }
	public double getCaptureRate() { return this.captureRate; }
	public int getExperienceGrowth() { return this.experienceGrowth; }
	public int getBaseEggSteps() { return this.baseEggSteps; }
	public String getName() { return this.name;}
	
	/*@Override
	public String toString() {
		return "Pokemon [name=" + name + ", attackPoints=" + attackPoints + ", baseEggSteps=" + baseEggSteps
				+ ", captureRate=" + captureRate + ", defensePoints=" + defensePoints + ", experienceGrowth="
				+ experienceGrowth + ", healthPoints=" + healthPoints + ", spAttackPoints=" + spAttackPoints
				+ ", spDefensePoints=" + spDefensePoints + ", type1=" + type1 + ", type2=" + type2 + ", speed=" + speed
				+ ", legendary=" + legendary + "]";
	}*/
	
	public String toString() {
		return String.format("speed:%s egg:%s exp:%s capture:%s legendary:%s", this.speed, this.baseEggSteps, this.experienceGrowth, this.captureRate, this.legendary);
	}
	public boolean isLegendary() {
		return this.legendary;
	}
	
}
