import javax.swing.table.TableCellRenderer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.HashMap;

public class MultiRenderer implements TableCellRenderer {
	
	private TableCellRenderer defaultRenderer = new DefaultTableCellRenderer();
	private Map<Class, TableCellRenderer> registeredRenderers = new HashMap<Class, TableCellRenderer>();
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
	{
		TableCellRenderer delegate = null;
		if (value != null) {
			delegate = getDelegate(value.getClass());
		}
		
		if (delegate == null) {
			delegate = defaultRenderer;
		}
		
		return delegate.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	}
	
	public void registerRenderer(Class type, TableCellRenderer renderer) {
		registeredRenderers.put(type, renderer);
	}
	
	private TableCellRenderer getDelegate(Class type) {
		TableCellRenderer delegate = null;
		while (type != null && delegate == null) {
			delegate = registeredRenderers.get(type);
			type = type.getSuperclass();
		}
		return delegate;
	}
	
}