import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;

public class App extends JFrame implements ActionListener {
    private JButton exitButton;
    private JButton option1Button;
    private JButton option2Button;
    private JButton option3Button;
    private JButton option4Button;
    private JButton option5Button;
    private JButton option6Button;
    private JButton option7Button;

    OutputPanel outputPanel;

    InputPanel inputPanel;

    public App() {
        setTitle("Menu");
        setSize(1200, 800);
        setLayout(new BorderLayout());


        // Create empty panel to hold table

        inputPanel = new InputPanel();
        outputPanel = new OutputPanel();
        Container container = getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        // Thêm hai panel vào container
        container.add(inputPanel);
        // Extracted method to create panel with buttons
        JPanel centerPanel = createCenterPanel();
        add(centerPanel, BorderLayout.CENTER);

        container.add(outputPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Extracted method to create panel with buttons
    private JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout());
        centerPanel.setBorder(BorderFactory.createTitledBorder("Step2:"));

        option1Button = new JButton("  fifo  ");
        option1Button.addActionListener(this);
        centerPanel.add(option1Button);

        option2Button = new JButton("optimal");
        option2Button.addActionListener(this);
        centerPanel.add(option2Button);
        option3Button = new JButton("   lru   ");
        option3Button.addActionListener(this);
        centerPanel.add(option3Button);

        option4Button = new JButton("   mru   ");
        option4Button.addActionListener(this);
        centerPanel.add(option4Button);
        option5Button = new JButton("   lfu   ");
        option5Button.addActionListener(this);
        centerPanel.add(option5Button);

        option6Button = new JButton("   mfu   ");
        option6Button.addActionListener(this);
        centerPanel.add(option6Button);

        option7Button = new JButton("   sc  ");
        option7Button.addActionListener(this);
        centerPanel.add(option7Button);


        exitButton = new JButton("Exit");
        exitButton.addActionListener(this);
        centerPanel.add(exitButton);

        return centerPanel;
    }

    // Extracted method to create and update table


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitButton) {
            System.exit(0);
        } else if (e.getSource() == option1Button) {
            Input input = updateInput(inputPanel);
            Output output = func_.fifo(input);
            updateTable(output);
        } else if (e.getSource() == option2Button) {
            Input input = updateInput(inputPanel);
            Output output = func_.optimal(input);
            updateTable(output);
        } else if (e.getSource() == option3Button) {
            Input input = updateInput(inputPanel);
            Output output = func_.lru(input);
            updateTable(output);
        } else if (e.getSource() == option4Button) {
            Input input = updateInput(inputPanel);
            Output output = func_.mru(input);
            updateTable(output);
        } else if (e.getSource() == option5Button) {
            Input input = updateInput(inputPanel);
            Output output = func_.lfu(input);
            updateTable(output);
        } else if (e.getSource() == option6Button) {
            Input input = updateInput(inputPanel);
            Output output = func_.mfu(input);
            updateTable(output);
        } else if (e.getSource() == option7Button) {
            Input input = updateInput(inputPanel);
            Output output = func_.fifo(input);
            updateTable(output);
        }

    }

    private void updateTable(Output output) {
        outputPanel.update(output);
    }

    private Input updateInput(InputPanel inputPanel) {
        Input input = new Input();
        input.setReference_string(inputPanel.getDataList());
        input.setPage_frames(inputPanel.getFrames());
        return input;
    }

    public static void main(String[] args) {
        new App();
    }
}