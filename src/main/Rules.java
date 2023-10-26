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
	
	public int getRebornMin() {
		return rebornMin;
	}
	public int getRebornMax() {
		return rebornMax;
	}
	public int getDieUnderpop() {
		return dieUnderpop;
	}
	public int getDieOverpop() {
		return dieOverpop;
	}
	
	public void setRebornMin(int rebornMin) {
		this.rebornMin = rebornMin;
	}
	public void setRebornMax(int rebornMax) {
		this.rebornMax = rebornMax;
	}
	public void setDieUnderpop(int dieUnderpop) {
		this.dieUnderpop = dieUnderpop;
	}
	public void setieOverpop(int dieOverpop) {
		this.dieOverpop = dieOverpop;
	}
}