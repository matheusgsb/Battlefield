package item;

public class Weapon extends Item {

	protected int range;
	protected int attackPts;

	/**
	 * Constructor of Weapon
	 * 
	 * @param name
	 * @param price
	 * @param attackPts
	 * @param range
	 * @throws ItemExceptionRange
	 * @throws ItemException
	 */
	public Weapon(String name, double price, int attackPts, int range)
			throws ItemExceptionName, ItemExceptionPrice,
			ItemExceptionAttackPts, ItemExceptionRange {
		super(name, price);

		if ((attackPts >= 5) && (attackPts <= 50))
			this.attackPts = attackPts;
		else
			throw new ItemExceptionAttackPts();

		if (range > 1)
			this.range = range;
		else
			throw new ItemExceptionRange();
		System.out
				.println("Created item " + this.getName() + " of type Weapon");

	}

	/**
	 * Copy Constructor of Weapon
	 * 
	 * @param copy
	 */
	public Weapon(Weapon copy) {
		super(copy);
		this.range = copy.range;
		this.attackPts = copy.attackPts;
		System.out
				.println("Created item " + this.getName() + " of type Weapon");
	}

	/**
	 * Weapon's clone method
	 */
	public Weapon clone() {
		return new Weapon(this);
	}

	/**
	 * Returns the range of an weapon
	 * 
	 * @return range
	 */
	public int getRange() {
		return range;
	}

	/**
	 * Returns the attack points
	 * 
	 * @return attackPts
	 */
	public int getAttackPts() {
		return attackPts;
	}

}
