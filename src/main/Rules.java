package main;

public class Rules {
	
	private int rebornMin;		//number of live neighbors over which a dead cell turns alive
	private int rebornMax;		//number of live neighbors under which a dead cell turns alive
	private int	dieUnderpop;	//number of live neighbors below which a living cell turns dead (doesn't die at the value)
	private int	dieOverpop;		//number of live neighbors over which a living cell turns dead (doesn't die at the value)
	
	public Rules(int rebornMin, int rebornMax, int dieUnderpop, int dieOverpop) {
		this.rebornMin = rebornMin;
		this.rebornMax = rebornMax;
		this.dieUnderpop = dieUnderpop;
		this.dieOverpop = dieOverpop;
	}
	
	int getRebornMin() {
		return rebornMin;
	}
	int getRebornMax() {
		return rebornMax;
	}
	int getDieUnderpop() {
		return dieUnderpop;
	}
	int getDieOverpop() {
		return dieOverpop;
	}
	
	void setRebornMin(int rebornMin) {
		this.rebornMin = rebornMin;
	}
	void setRebornMax(int rebornMax) {
		this.rebornMax = rebornMax;
	}
	void setDieUnderpop(int dieUnderpop) {
		this.dieUnderpop = dieUnderpop;
	}
	void setieOverpop(int dieOverpop) {
		this.dieOverpop = dieOverpop;
	}
}