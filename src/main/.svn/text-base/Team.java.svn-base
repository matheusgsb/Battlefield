package main;

import java.util.ArrayList;
import character.Character;

public class Team {

	private String name;
	private Colors color;
	private int win = 0;
	private int lose = 0;
	private int draw = 0;
	private ArrayList<Character> characters = new ArrayList<Character>();
	
	/**
	 * Constructor of Team
	 * 
	 * @param name
	 * @param colorTeam
	 */
	public Team (String name , Colors colorTeam) {
		this.name = name;
		this.color = colorTeam;
		System.out.println("Created "+ name +" team of color " + colorTeam );
	}
	
	/**
	 * Increases the number of victories
	 */
	public void victory () {
		this.win++;
		System.out.println ("Team " + this.name + " won the game!");
	}
	
	/**
	 * Increases the number of draws 
	 */
	public void draw () {
		this.draw++;
		System.out.println ("Team " + this.name + " tied the game!");
	}
	
	/**
	 * Increases the number of defeats
	 */
	public void defeat () {
		this.lose++;
		System.out.println ("Team " + this.name + " lost");
	}
	
	/**
	 * Removes a character from the team by its index
	 * 
	 * @param index
	 */
	public void removeCharacter (int index) {
		this.characters.remove(index);
	}
	
	/**
	 * Removes a character from the team
	 * 
	 * @param character
	 */
	public void removeCharacter (Character character) {
		this.characters.remove(character);
	}
	
	/**
	 * Adds a character to the team
	 * 
	 * @param character
	 * @return result of the addition
	 */
	public boolean addCharacter (Character character ) {
		boolean flag = true;
		
		for (Character c : characters) {
			if (c.getName().equals(character.getName()))
				flag = false;
		}
		
		if (flag) {
			character.setTeamColor(this.color);
			this.characters.add(character);
		}
		
		return flag;
	}
	
	/**
	 * Print the characters of the team
	 */
	public void printCharacters() {
		for(Character c : this.characters)
			System.out.println(c.getName());
	}
	
	/**
	 * Returns a String with team's information
	 */
	public String toString() {
		String result;
		
		result = this.name + " - " + this.color.name() + " : ";
		for(Character c : this.characters) {
			result.concat(c.getName());
			result.concat("  ");
		}
		
		System.out.println("toString method returned: " + result);
		return result; 
	}
	
	/**
	 * Searches for an specific character name on the team
	 * 
	 * @param alias
	 * @return character
	 */
	public Character searchChar (String alias) {
		for(Character c : this.characters) {
			if (c.getName().equals(alias))
				return c;
		}		
		return null;
	}
	
	/** 
	 * Returns the results of the team
	 */
	public String getResults () {
		return  "Vitorias: " + this.win +", Empates: " + this.draw +", Derrotas: " + this.lose + ".";
	}
	
	/**
	 * Returns the characters of the team
	 * 
	 * @return characters
	 */
	public ArrayList<Character> getCharacters() {
		return this.characters;
	}

	/**
	 * Returns the team's name
	 * 
	 * @return name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Returns the team's color
	 * 
	 * @return color
	 */
	public Colors getColor() {
		return this.color;
	}

}
