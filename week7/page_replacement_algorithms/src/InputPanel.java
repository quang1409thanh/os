import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;

public class InputPanel extends JPanel {
    private final JTextField inputField;
    private JButton addButton;
    private List<Integer> dataList;
    private int frames;
    private JSpinner intSpinner;

    public void clearData() {
        dataList.clear();
    }

    public InputPanel() {
        setLayout(new FlowLayout());
        setBorder(BorderFactory.createTitledBorder("Step1:--------------------------- reference strings -------------------------------------------------------------------------------------- frames: "));

        inputField = new JTextField(50); // độ rộng của text field là 50
        addButton = new JButton("Add");
        dataList = new ArrayList<>();
        intSpinner = new JSpinner(new SpinnerNumberModel(0, Integer.MIN_VALUE, Integer.MAX_VALUE, 1));

        add(inputField);
        add(intSpinner);
        add(addButton);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearData();
                String input = inputField.getText();
                String[] numbers = input.split(" ");

                for (String number : numbers) {
                    try {
                        int value = Integer.parseInt(number);
                        dataList.add(value);
                    } catch (NumberFormatException ex) {
                        System.out.println("Invalid input: " + number);
                    }
                }

                // Lưu giá trị intValue vào biến frame của chương trình ở đây
                frames = (int) intSpinner.getValue();

                inputField.selectAll();
                inputField.requestFocus();
            }
        });

    }

    public List<Integer> getDataList() {
        return dataList;
    }

    public int getFrames() {
        return frames;
    }
}
