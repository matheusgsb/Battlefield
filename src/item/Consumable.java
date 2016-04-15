package item;
import character.Character;

public interface Consumable {
	
		/**
		 * Returns the restore points
		 * 
		 * @return retorePts
		 */
		public int restore();
		
		/**
		 * Returns the revive points
		 * 
		 * @return revivePts
		 */
		public int revive();
		
		/**
		 * Verifies if the item is consumable by the character
		 * 
		 * @param character
		 * @return true/false
		 */
		public boolean consumableBy(Character  character);
		
		/**
		 * Performs consumption
		 * 
		 * @param character
		 */
		public void consume(Character character);
		
		/**
		 * Consumable's clone method
		 * 
		 * @return new Consumable
		 */
		public Consumable clone();
		
}
