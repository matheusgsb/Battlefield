package main;

import character.Character;
import java.util.*;

public class Board {

	private Map<Colors, Team> teams = new HashMap<Colors, Team>();
	private SortedSet<BoardPosition> positions = new TreeSet<BoardPosition>();
	private int width;
	private int height;

	/**
	 * Constructor of Board
	 */
	public Board() {
		this.width = this.height = 5;
		System.out.println("Start Board  width = " + this.width
				+ " and height = " + this.height);
	}

	/**
	 * Constructor with dimensions
	 * 
	 * @param width
	 * @param heigth
	 */
	public Board(int width, int height) {
		if (width > 0)
			this.width = width;
		else
			this.width = 5;
		if (height > 0)
			this.height = height;
		else
			this.height = 5;
		System.out.println("Start Board  width = " + this.width
				+ " and height " + this.height);	
	}

	/**
	 * Sets character's position
	 * 
	 * @param xy
	 * @param colorTeam
	 * @param character
	 */
	public synchronized void setCharacterPosition(int xy, Colors colorTeam,
			Character character) {

		if (xy >= 0 && xy <= this.height * this.width) {
			
			for (BoardPosition bp : positions) {
				if (bp.getOccup().equals(character)) {
					positions.remove(bp);
					//System.out.println("Character already had a xy position setted");
					break;
				}
			}
			
			Team t = teams.get(colorTeam);
			if (t != null) {
				Character c = t.searchChar(character.getName());
				if (c != null) {
					while (!positions.add(new BoardPosition(c, xy))) {
						xy = (xy > this.height * this.width / 2) ? xy - 1
								: xy + 1;
					}
					System.out.println(c.getName() + "'s position from team "
							+ colorTeam + " was changed to " + xy);
				}
			}
		}
	}

	/**
	 * Includes a new team to the board
	 * 
	 * @param color
	 * @param team
	 * 
	 * @return true if added the new team. In other case, returns false
	 */
	public boolean addTeam(Colors color, Team team) {
		boolean flag = true;

		for (Map.Entry<Colors, Team> entry : this.teams.entrySet()) {
			if (entry.getKey() == color)
				flag = false;
		}

		if (this.teams.size() < 5 && flag)
			this.teams.put(color, team);
		
		return flag;
	}

	/**
	 * Removes a team from the board by its color
	 * 
	 * @param colorTeam
	 */
	public void removeTeam(Colors colorTeam) {
		for (Map.Entry<Colors, Team> entry : this.teams.entrySet()) {
			if (entry.getKey() == colorTeam)
				this.removeTeam(entry.getKey());
		}
	}

	/**
	 * Returns the team with the color passed by parameters
	 * 
	 * @param colorTeam
	 */
	public Team getTeam(Colors colorTeam) {
		for (Map.Entry<Colors, Team> entry : this.teams.entrySet()) {
			if (entry.getKey() == colorTeam)
				return entry.getValue();
		}
		return null;
	}

	/**
	 * Sets the board's width
	 * 
	 * @param width
	 */
	public void setWidth(int width) {
		if (width > 0)
			this.width = width;
	}

	/**
	 * Sets the board's height
	 * 
	 * @param height
	 */
	public void setHeight(int height) {
		if (height > 0)
			this.height = height;
	}

	/**
	 * Returns the board's width
	 * 
	 * @return width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Returns the board's height
	 * 
	 * @return height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Verifies attack's range
	 * 
	 * @param killer
	 * @param victim
	 * @return true happen if the attack
	 */
	public boolean attack(Character killer, Character victim) {
		
		if (killer.getTeamColor().equals(victim.getTeamColor())) {
			System.out.println("Attack failed: " + killer.getName() + " didn't attack " + victim.getName() +
					". They're from the same team");
			return false;
		}
		
		BoardPosition positionKiller = null, positionVictim = null;
		
		
		for (BoardPosition pos : this.positions) {
			if ((pos.getOccup().getName().equals(killer.getName()) && (pos.getOccup().getTeamColor().equals(killer.getTeamColor())))){
				positionKiller = pos;
			} else if ((pos.getOccup().getName().equals(victim.getName()) && (pos.getOccup().getTeamColor().equals(victim.getTeamColor())))){
				positionVictim = pos;
			}
		}

		if (positionKiller == null || positionVictim == null) {
			System.out.println("Attack failed: " + killer.getName() + " didn't attack " + victim.getName() + 
					". Error during the position search");
			return false;
		}
		
		// Verifies if they're close enough
		int xKiller, yKiller, xVictim, yVictim;
		
		xKiller = (positionKiller.getXY()-1) % this.width;
		yKiller = (positionKiller.getXY()-1) / this.width; 
		
		xVictim = (positionVictim.getXY()-1) % this.width;
		yVictim = (positionVictim.getXY()-1) / this.width;
		
		//PS: -1 because to do the count we assume that the x,y positions start with 0
		
		double distance = Math.sqrt( (xKiller - xVictim)*(xKiller - xVictim) + 
				(yKiller - yVictim)*(yKiller - yVictim));
		
		if  (distance <= positionKiller.getOccup().getRangeWeapon()) {
			
			positionKiller.getOccup().attack(positionVictim.getOccup(), 1/distance);
			//System.out.println("K: ("+xKiller+","+yKiller+") V: ("+xVictim+","+yVictim+") Range: "+killer.getRangeWeapon()+ " Dist: "+distance);
			return true;
			
		} else {
			System.out.println("Attack failed: " + killer.getName() + " didn't attack " + victim.getName() + ". They're not close enough");
			//System.out.println("K: ("+xKiller+","+yKiller+") V: ("+xVictim+","+yVictim+") Range: "+killer.getRangeWeapon()+ " Dist: "+distance);
			return false;	
		}
	}
	
	/**
	 * Verifies if a boardPosition is occupied
	 * 
	 * @param row
	 * @param col
	 * @return true/false
	 */
	public boolean isOccupied(int row, int col) {
	
		int xy = this.width*row + col;
		
		for (BoardPosition pos : this.positions) {
			if (pos.getXY() == xy)
				return true; 
		}
		
		return false;
	}
	
	/**
	 * Returns the character at an specific boardPosition
	 *  
	 * @param row
	 * @param col
	 * @return character
	 */
	public Character characterAt(int row, int col) {
	
		int xy = this.width*row + col;
		
		for (BoardPosition pos : this.positions) {
			if (pos.getXY() == xy)
				return pos.getOccup(); 
		}
		
		return null;
		
	}
	
}
