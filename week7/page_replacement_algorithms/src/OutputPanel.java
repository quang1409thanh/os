import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.*;
import javax.swing.table.*;

public class OutputPanel extends JPanel {
    public JTable table;

    public OutputPanel() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
    }

    public JTable createTable(Output output) {
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();

        // Add existing rows to data
        Vector<Object> row1 = new Vector<Object>();
        row1.add("References ");
        row1.addAll(output.getReference_string());
        data.add(row1);

        Vector<Object> row2 = new Vector<Object>();
        row2.add("Faults     ");
        row2.addAll(output.getFaults());
        data.add(row2);

        Vector<Object> row3 = new Vector<Object>();
        row3.add("Removed    ");
        row3.addAll(output.getRemoved());
        data.add(row3);

        Vector<Object> columnNames = new Vector<Object>();
        columnNames.add(output.getType());
        for (int i = 0; i < output.getReference_string().size(); i++) {
            columnNames.add((i + 1));
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            private static final long serialVersionUID = 1L;
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(model);
        TableColumnModel columnModel = table.getColumnModel();
        TableColumn firstColumn = columnModel.getColumn(0);
        // Thiết lập CustomCellRenderer cho bảng
        return table;
    }

    public void update(Output output) {
        setBorder(BorderFactory.createTitledBorder("results: "));
        table = createTable(output);
        JScrollPane scrollPane = new JScrollPane(table);
//        , JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS
        scrollPane.setPreferredSize(new Dimension(1160, 200));
        JLabel pageF_R = new JLabel("<html><div style='text-align: left;'>Page_Faults: " + output.getPage_Faults() + "<br>" +
                "Page_Replacement: " + output.getPage_Replacements() + "</div></html>");
        pageF_R.setForeground(Color.RED);
        this.removeAll();
        this.add(scrollPane, BorderLayout.NORTH);
        this.add(pageF_R, BorderLayout.WEST);
        this.revalidate();
        this.repaint();
    }

}
