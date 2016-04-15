package item;

public class ItemExceptionRevivePts extends ItemException {

	private static final long serialVersionUID = 6780346794197352301L;

	/**
	 * Constructor of ItemExceptionRevivePts
	 */
	public ItemExceptionRevivePts() {
		super("Exception: revivePts must be a value higher than 1");
		
	}

}
