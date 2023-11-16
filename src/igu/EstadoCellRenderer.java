package igu;

import java.awt.Color;
import java.awt.Component;


import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class EstadoCellRenderer extends DefaultTableCellRenderer{
	@Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);        
        Object estadoValue = table.getValueAt(row, 1);
        if ("Liberada".equals(estadoValue)) {
            cellComponent.setBackground(Color.GREEN);
        } else if ("Reservada".equals(estadoValue)) {
            cellComponent.setBackground(Color.YELLOW);
        } else if ("Ocupada".equals(estadoValue)) {
            cellComponent.setBackground(Color.RED);
        } else {
            cellComponent.setBackground(table.getBackground());
        }
        return cellComponent;
    }
}
