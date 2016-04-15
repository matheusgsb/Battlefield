package character;

public class Fighter extends Character {
	
	protected int power;

	/**
	 * Constructor of Fighter
	 * 
	 * @param alias
	 * @param power
	 */
	public Fighter(String alias, int power)throws NullPointerException {
		super(alias);
		this.power = power;
	}
	
	/**
	 * Constructor of Fighter with more parameters
	 * 
	 * @param alias
	 * @param power
	 * @param strength
	 * @param speed
	 * @param dexterity
	 * @param constitution
	 * @throws NullPointerException
	 */
	public Fighter(String alias, int power, int strength, int speed, int dexterity, int constitution) throws NullPointerException {
		super(alias, strength, speed, dexterity, constitution);
		this.power = power;
	}
	
	/**
	 * Copy Constructor of Fighter
	 * 
	 * @param copy
	 */
	public Fighter (Fighter copy) {
		super(copy);
		this.power = copy.power;
	}

	/**
	 * Returns the fighter's defense points
	 * 
	 * @return points = ((constitution*.6+dexterity*.1+speed*.3)+item_def_pts)*(XP/6)
	 */
	@Override
	protected int getDefensePoints() {
		//System.out.println("Defense points of "+ this.getName() +" was calculated");
		return (int) ((((double)this.constitution * 0.6 + (double)this.dexterity * 0.1 + (double)this.speed * 0.3) + this.getItemDefPts()) * ((double)this.XP / 6));
		
	}

	/**
	 * Returns the fighter's attack points
	 * 
	 * @return points = ((strength*.6+dexterity*.4)+power*.1)+item_att_pts)*(XP/2)
	 */
	@Override
	protected int getAttackPoints() {
		//System.out.println("Attack points of "+ this.getName() +" was calculated");
		return (int)((((double)this.strength * 0.6 + (double)this.dexterity * 0.4 + (double)this.power * 0.1) + this
				.getItemAttPts()) * ((double)this.XP / 2));
	}

	/**
	 * Attacks a character
	 * 
	 * @param character
	 * @param reduction
	 */
	@Override
	public void attack(Character character, double reduction) {
		int damage = 0;
		if ( Math.random() >= 0.1/this.XP){
			damage = (int)(this.getAttackPoints() - character.getDefensePoints()) + this.rnd(-5,5);
			damage *= reduction;
			if (damage < 1)
				damage = 1;
			if ( Math.random() <= ( 0.02*(this.XP/2)) )
				damage *= 2;	
		}
		System.out.println(this.getName() +" attacked " + character.getName() + " with damage of " + damage);
		character.sufferDamage(damage);
	}

}
