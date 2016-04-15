package character;

import item.*;
import java.util.*;
import main.Colors;

public abstract class Character {

	private String alias;
	private Map<Integer, Item> inventory = new HashMap<Integer, Item>();
	private Consumable consumableItem;
	protected int HP = 100;
	protected int maxHP = 100;
	protected int XP = 1;
	protected int strength = 25;
	protected int speed = 25;
	protected int dexterity = 25;
	protected int constitution = 25 ;
	protected Colors teamColor;
	
	/**
	 * Constructor of Character
	 * 
	 * @param alias
	 */
	public Character(String alias)throws NullPointerException {
		if(alias == null)throw new NullPointerException("Exceção-> Nome de Character nulo não é permitido");
		this.alias = alias;
		System.out.println("Created "+ alias +" Character " );
	}
	
	/**
	 * Constructor of Character with more parameters
	 * 
	 * @param alias
	 * @param strength
	 * @param speed
	 * @param dexterity
	 * @param constitution
	 * @throws NullPointerException
	 */
	public Character(String alias, int strength, int speed, int dexterity, int constitution)throws NullPointerException {
		if(alias == null)throw new NullPointerException("Excecao: nome de character nulo nao e permitido");
		this.alias = alias;
		this.strength = strength;
		this.speed = speed;
		this.dexterity = dexterity;
		this.constitution = constitution;
		System.out.println("Created "+ alias +" Character with attributes:  " + strength + " " + speed + " " + dexterity + " " + constitution); 
	}

	/**
	 * Returns the character's XP
	 * 
	 * @return XP
	 */
	public int getXP() {
		return XP;
	}

	/**
	 * Verifies if the character has or not a consumable item
	 * 
	 * @return true/false
	 */
	public boolean hasConsumable() {
	 
		if (this.consumableItem != null)
			return true;
		return false;
		
	}

	/**
	 * Copy Constructor of Character
	 * 
	 * @param copy
	 */
	public Character (Character copy) {
		this.HP = copy.HP;
		this.maxHP = copy.maxHP;
		this.XP = copy.XP;
		this.alias = copy.alias;
		this.strength = copy.strength;
		this.speed = copy.speed;
		this.dexterity = copy.dexterity;
		this.constitution = copy.constitution ;
		if(copy.consumableItem != null)
			this.consumableItem = copy.consumableItem.clone();
		
		for (Map.Entry<Integer, Item> entry : copy.inventory.entrySet()) {
			this.inventory.put(entry.getKey(), entry.getValue().clone());
		}
	}

	/**
	 * Attacks a character
	 * 
	 * @param character
	 * @param reduction
	 */
	public abstract void attack(Character character, double reduction);

	/**
	 * Method is invoked when a character suffers an attack
	 * 
	 * @param damage
	 */
	protected void sufferDamage (int damage){
		this.HP -= damage;
		if ( this.HP < 0 ){
			this.HP = 0;
			System.out.println(this.alias + " is dead");
		}
		//isn'tDead
	}
	
	/**
	 * Method add XP to the character
	 *  
	 * @param XP
	 */
	public void addXP(int XP) {
		if ((this.XP + XP <= 100) && (this.XP + XP >= 1)) {
			this.XP = this.XP + XP;
			
			double HP = (XP * (0.3 * this.HP));
			this.addMaxHP((int)HP);
			this.addHP((int)HP);
			
			System.out.println("Added " + XP + "XP and " + (int)HP + "HP");
		}
	}
	
	/**
	 * Method add HP to the character
	 *  
	 * @param HP
	 */
	public void addHP(int HP) {
		if (HP > 0)
			this.HP += HP;
		if (this.HP > this.maxHP)
			this.HP = this.maxHP;
	}

	/**
	 * Returns the character's defense points
	 * 
	 * @return points
	 */
	protected int getDefensePoints() {
		return 0;
	}

	/**
	 * Returns the character's attack points
	 * 
	 * @return points
	 */
	protected int getAttackPoints() {
		return 0;
	}

	/**
	 * Returns the sum of the items' attack points 
	 * 
	 * @return attack points
	 */
	protected int getItemAttPts() {
		int points = 0;

		for (Map.Entry<Integer, Item> entry : this.inventory.entrySet()) {
			points += entry.getValue().getAttackPts();
		}

		return points;
	}

	/**
	 * Returns the sum of the items' defense points
	 * 
	 * @return defense points
	 */
	protected int getItemDefPts() {
		int points = 0;

		for (Map.Entry<Integer, Item> entry : this.inventory.entrySet()) {
			points += entry.getValue().getDefensePts();
		}

		return points;
	}

	/**
	 * Returns the character's name
	 * 
	 * @return alias
	 */
	public String getName() {
		return alias;
	}

	/**
	 * Adds an item to the inventory
	 * 
	 * @param key, item
	 */
	public void addItem (int key, Item item) {
		this.inventory.put(key, item);
	}
	
	/**
	 * Removes an item from the inventory by its key
	 * 
	 * @param key
	 */
	public void dropItem (int key) {
		this.inventory.remove(key);
	}
	
	/**
	 * Returns an item that will be given to another character
	 * 
	 * @param key
	 * @return item
	 */
	public Item giveItem (int key) {
		return this.inventory.get(key);
	}
	
	/**
	 * Returns a random number between min and max
	 * 
	 * @param min
	 * @param max
	 * @return the random number
	 */
	protected int rnd(int min, int max) {
		return (int) (min + (max - min) * Math.random());
	}
	
	/**
	 * Sets a character's consumable item
	 * 
	 * @param consumable
	 */
	public void setConsumable (Consumable consumable) {
		this.consumableItem = consumable;
	}
	
	/**
	 * @return the HP
	 */
	public int getHP() {
		return HP;
	}
	
	
	/**
	 * Uses a consumable item 
	 * 
	 * @param character
	 */
	public void useConsumable(Character character) {
		if (this.consumableItem.consumableBy(character)) {
			this.consumableItem.consume(character);
			this.consumableItem = null;
			System.out.println(this.getName() + "'s consumable item was consumed by " + character.getName());
		} else
			System.out.println(this.getName() + "'s consumable item wasn't consumed by " + character.getName());
	}
	
	/**
	 * Returns the range of the character's weapon
	 * 
	 * @return range
	 */
	public int getRangeWeapon(){
		int range = 0 ;

		for (Map.Entry<Integer, Item> entry : this.inventory.entrySet()) {
			range = (entry.getValue().getRange() > range) ? entry.getValue().getRange(): range ;
			
		}
		
		return range;
		
	}
	
	/**
	 * Sets the character's teamColor
	 * 
	 * @param teamColor
	 */
	public void setTeamColor(Colors teamColor) {
		this.teamColor = teamColor;
	}
	
	/**
	 * Returns the character's teamColor
	 * 
	 * @return teamColor
	 */
	public Colors getTeamColor() {
		return this.teamColor;
	}
	
	/**
	 * Returns the character's maxHP
	 * 
	 * @return maxHP
	 */
	public int getMaxHP() {
		return this.maxHP;
	}
	
	/**
	 * Increases the character's maxHP
	 * 
	 * @param increase
	 */
	public void addMaxHP(int inc) {
		if (inc > 0)
			this.maxHP += inc;
	}
	
	/**
	 * Returns the size of character's inventory
	 * @return
	 */
	public int inventorySize() {
		
		return this.inventory.size();
		
	}
	
}
