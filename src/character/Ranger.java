package character;

public class Ranger extends Character {

	protected int accuracy;

	/**
	 * Constructor of Ranger
	 * 
	 * @param alias
	 * @param accuracy
	 */
	public Ranger(String alias,  int accuracy)throws NullPointerException  {
		super(alias);
		this.accuracy = accuracy;
	}
	
	/**
	 * Constructor of Ranger with more parameters
	 * 
	 * @param alias
	 * @param accuracy
	 * @param strength
	 * @param speed
	 * @param dexterity
	 * @param constitution
	 * @throws NullPointerException
	 */
	public Ranger(String alias, int accuracy, int strength, int speed, int dexterity, int constitution) throws NullPointerException {
		super(alias, strength, speed, dexterity, constitution);
		this.accuracy = accuracy;
	}
	
	/**
	 * Copy Constructor of Ranger
	 * 
	 * @param copy
	 */
	public Ranger (Ranger copy) {
		super(copy);
		this.accuracy = copy.accuracy;
	}
	
	/**
	 * Returns the ranger's defense points
	 * 
	 * @return points = ((constitution*.5+dexterity*.2+speed*.3)+item_def_pts)*(XP/6)
	 */
	@Override
	protected int getDefensePoints() {
		//System.out.println("Defense points of " + this.getName() +" was calculated");
		return (int) ((((double)this.constitution * 0.5 + (double)this.dexterity * 0.2 + (double)this.speed * 0.3) + this
				.getItemDefPts()) * ((double)this.XP / 6));
	}

	/**
	 * Returns the ranger's attack points
	 * 
	 * @return points = ((Strength*.5+dexterity*.5)+accuracy*.1)+item_att_pts)*(XP/2)
	 */
	@Override
	protected int getAttackPoints() {
		//System.out.println("Attack points of "+this.getName() +" was calculated");
		return (int) ((((double)this.strength * 0.5 + (double)this.dexterity * 0.5 + (double)this.accuracy * 0.1) + this
				.getItemAttPts()) * ((double)this.XP / 2));
	}
	
	/**
	 * Attacks a character
	 * 
	 * @param character
	 */
	@Override
	public void attack(Character character, double reduction) {
		int damage = 0;
		if ( Math.random() >= 0.07/this.XP){
			damage = (int)(this.getAttackPoints() - character.getDefensePoints()) + this.rnd(-5,5);
			if (damage < 1)
				damage =1;
			if ( Math.random() <= ( 0.02*(this.XP/2)) )
				damage *= 2;	
		}
		System.out.println(this.getName() +" attacked " + character.getName() + " with damage of " + damage);
		character.sufferDamage(damage);
	}
	
}
