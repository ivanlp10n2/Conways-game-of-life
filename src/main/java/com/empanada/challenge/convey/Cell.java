package com.empanada.challenge.convey;

/**
 * @author ivan
 * 
 * Cell is intend to have its state and its future state (so for update its values)
 * 
 */
public class Cell {

	private Boolean alive;
	private Boolean nextState;
	
	public Cell() {
		this.alive = Boolean.FALSE;
		this.nextState = Boolean.FALSE;
	}
	public Boolean isAlive() {
		return alive;
	}

	public void setAlive(Boolean alive) {
		this.alive = alive;
	}

	public Boolean isNextState() {
		return nextState;
	}

	public void setNextState(Boolean nextState) {
		this.nextState = nextState;
	}
	
	
}
