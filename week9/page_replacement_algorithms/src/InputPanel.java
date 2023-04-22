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
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBorder(BorderFactory.createTitledBorder("Step1: "));

        JLabel inputLabel = new JLabel("Input:");
        inputField = new JTextField("Hay nhap di ex: 1 2 3 4 5 6 5 .....",50);
        addButton = new JButton("Add");
        dataList = new ArrayList<>();

        JLabel intSpinnerLabel = new JLabel("Frames:");
        intSpinner = new JSpinner(new SpinnerNumberModel(0, Integer.MIN_VALUE, Integer.MAX_VALUE, 1));
        inputField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                if (inputField.getText().isEmpty()) {
                    inputField.setText("Hay nhap di ex: 1 2 3 4 5 6 5 .....");
                    inputField.setForeground(Color.GRAY);
                    Font font = inputField.getFont().deriveFont(Font.ITALIC);
                    inputField.setFont(font);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (inputField.getText().equals("Hay nhap di ex: 1 2 3 4 5 6 5 .....")) {
                    inputField.setText("");
                    inputField.setForeground(Color.BLACK);
                    Font font = inputField.getFont().deriveFont(Font.PLAIN);
                    inputField.setFont(font);
                }
            }
        });
        inputField.setNextFocusableComponent(intSpinner);
        intSpinner.setNextFocusableComponent(inputField);

        add(inputLabel);
        add(inputField);
        add(intSpinnerLabel);
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
