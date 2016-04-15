package item;

import character.Character;

public class RevivePotion extends Item implements Consumable {

	private int revivePts;

	/**
	 * Constructor of RevivePotion
	 * 
	 * @param name
	 * @param price
	 * @param revivePts
	 * @throws ItemExceptionRevivePts
	 * @throws ItemException
	 */
	public RevivePotion(String name, double price, int revivePts)
			throws ItemExceptionName, ItemExceptionPrice,
			ItemExceptionRevivePts {
		super(name, price);
		if (revivePts > 1)
			this.revivePts = revivePts;
		else
			throw new ItemExceptionRevivePts();
		System.out.println("Created item " + this.getName()
				+ " of type RevivePotion");
	}

	/**
	 * Constructor of RevivePotion
	 * 
	 * @param potion
	 */
	public RevivePotion(RevivePotion potion) {
		super(potion);
		this.revivePts = potion.revivePts;
		System.out.println("Created item " + this.getName()
				+ " of type RevivePotion");
	}

	/**
	 * RevivePotion's clone method
	 */
	public RevivePotion clone() {
		return new RevivePotion(this);
	}

	/**
	 * Returns the restore points
	 * 
	 * @return 0
	 */
	@Override
	public int restore() {
		return 0;
	}

	/**
	 * Returns the revive points
	 * 
	 * @return revivePts
	 */
	@Override
	public int revive() {
		return this.revivePts;
	}

	/**
	 * Verifies if the item is consumable by the character
	 * 
	 * @param character
	 * @return true/false
	 */
	@Override
	public boolean consumableBy(Character character) {
		if (character.getHP() == 0)
		{
			System.out.println("Revive potion can be consumabled by " + character.getName());
			return true;
		} else {
			System.out.println("Revive potion can't be consumabled by " + character.getName() + ". It's HP is " + character.getHP());
			return false;
		
		}
	}

	/**
	 * Performs consumption
	 * 
	 * @param character
	 */
	@Override
	public void consume(Character character) {
		if (this.consumableBy(character)) {
			character.addHP(this.revive());
			System.out.println(character.getName() + " is alive and gained "
					+ this.revivePts + "HP. " + "It's new HP is "
					+ character.getHP());
		}
	}

}
