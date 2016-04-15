package item;

public abstract class Item {

	private String name;
	private double price;

	/**
	 * Constructor of Item
	 * 
	 * @param name
	 * @param price
	 * @throws ItemException 
	 */
	public Item(String name, double price) throws ItemExceptionName,ItemExceptionPrice{
		
		if (name == null)throw new ItemExceptionName();
		this.name = name;

		if (price <= 0)throw new ItemExceptionPrice();
		this.price = price;
		
	}

	/**
	 * Copy Constructor of Item
	 * 
	 * @param copy
	 */
	public Item( Item copy) {
		this.name = copy.name;
		this.price = copy.price;
	}
	
	/**
	 * Item's clone method
	 */
	public abstract Item clone();
	
	/**
	 * Returns the name of an item
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the attack points of an item
	 * 
	 * @return attackpts (0)
	 */
	public int getAttackPts() {
		return 0;
	}
	
	/**
	 * Returns the defense points of an item 
	 * 
	 * @return defensepts (0)
	 */
	public int getDefensePts() {
		return 0;
	}
	
	/**
	 * Returns the price of an item
	 * 
	 * @return price
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * Returns the range of an item
	 * 
	 * @return range (0)
	 */
	public int getRange() {
		return 0;
	}
	
}
