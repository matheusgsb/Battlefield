package item;

public class Armor extends Item {

	protected int defensePts;

	/**
	 * Constructor of Armor
	 * 
	 * @param name
	 * @param price
	 * @param defensePts
	 * @param flexibility
	 * @throws ItemExceptionDefensePts
	 * @throws ItemException
	 */
	public Armor(String name, double price, int defensePts)
			throws ItemExceptionName, ItemExceptionPrice,
			ItemExceptionDefensePts {
		super(name, price);
		if ((defensePts >= 1) && (defensePts <= 30))
			this.defensePts = defensePts;
		else
			throw new ItemExceptionDefensePts();
		System.out.println("Created item " + this.getName() + " of type Armor");
	}

	/**
	 * Copy Constructor of Armor
	 ** 
	 * @param copy
	 */
	public Armor(Armor copy) {
		super(copy);
		this.defensePts = copy.defensePts;
		System.out.println("Created item " + this.getName() + " of type Armor");
	}

	/**
	 * Armor's clone method
	 */
	public Armor clone() {
		return new Armor(this);
	}

	/**
	 * Returns the defense points
	 * 
	 * @return defensePts
	 */
	public int getDefensePts() {
		return defensePts;
	}

}
