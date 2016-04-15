package main;

import character.Character;

public class BoardPosition implements Comparable<BoardPosition> {

	private int xy;
	private Character occupied;
	
	/**
	 * Constructor of BoardPosition
	 * 
	 * @param occupied
	 * @param xy
	 */
	public BoardPosition(Character occupied, int xy){
		this.occupied = occupied;
		if (xy > 0)
			this.xy = xy;
		//System.out.println("Created " + this.occupied.getName() + "'s position with xy = " + this.xy);
	}
	
	/**
	 * Returns xy position
	 * 
	 * @return xy
	 */
	public int getXY() {
		return this.xy;
	}
	
	/**
	 * Sets xy position
	 * 
	 * @param xy
	 */
	public void setXY(int xy) {
		if (xy>0)
			this.xy = xy;
	}
	
	/**
	 * Returns the character that occupies the board position
	 * @return
	 */
	public Character getOccup() {
		return this.occupied;
	}
	
	/**
	 * Used to compare into the set
	 */
	@Override
	public int compareTo(BoardPosition o) {
		return this.xy - o.xy;
	}
	
	
}
