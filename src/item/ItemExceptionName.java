package item;

public class ItemExceptionName extends ItemException {
	
	private static final long serialVersionUID = 6252449796988191993L;

	/**
	 * Constructor of ItemExceptionName
	 */
	public ItemExceptionName(){
		super("Exception: name can't be null");
	}
}
