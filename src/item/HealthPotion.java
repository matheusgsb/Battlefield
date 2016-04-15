package item;

import character.Character;

public class HealthPotion extends Item implements Consumable {

	private int restorePts;

	/**
	 * Constructor of HealthPotion
	 * 
	 * @param name
	 * @param price
	 * @param restorePts
	 * @throws ItemExceptionRestorePts
	 * @throws ItemException
	 */
	public HealthPotion(String name, double price, int restorePts)
			throws ItemExceptionName, ItemExceptionPrice,
			ItemExceptionRestorePts {
		super(name, price);
		if (restorePts > 1)
			this.restorePts = restorePts;
		else
			throw new ItemExceptionRestorePts();
		System.out.println("Created item " + this.getName()
				+ " of type HealthPotion");
	}

	/**
	 * Constructor of HealthPotion
	 * 
	 * @param potion
	 */
	public HealthPotion(HealthPotion potion) {
		super(potion);
		this.restorePts = potion.restorePts;
		System.out.println("Created item " + this.getName()
				+ " of type HealthPotion");
	}

	/**
	 * HealthPotion's clone method
	 */
	public HealthPotion clone() {
		return new HealthPotion(this);
	}

	/**
	 * Returns the restore points
	 * 
	 * @return restoPts
	 */
	@Override
	public int restore() {
		return this.restorePts;
	}

	/**
	 * Returns the revive points
	 * 
	 * @return 0
	 */
	@Override
	public int revive() {
		return 0;
	}

	/**
	 * Verifies if the item is consumable by the character
	 * 
	 * @param character
	 * @return true/false
	 */
	@Override
	public boolean consumableBy(Character character) {

		if ((character.getHP() != character.getMaxHP())
				&& (character.getHP() != 0))
			return true;
		else
			return false;
	}

	/**
	 * Performs consumption
	 * 
	 * @param character
	 */
	@Override
	public void consume(Character character) {

		if (this.consumableBy(character)) {
			character.addHP(this.restore());
			System.out.println(character.getName() + " gained "
					+ this.restorePts + "HP. " + "It's new HP is "
					+ character.getHP());
		}

	}

}
