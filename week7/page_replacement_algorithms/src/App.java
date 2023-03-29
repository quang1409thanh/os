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
        setTitle("page_replacement_algorithms");
        setSize(1200, 800);
        setLayout(new FlowLayout());


        // Create empty panel to hold table

        inputPanel = new InputPanel();
        outputPanel = new OutputPanel();
        Container container = getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        JPanel titlePanel = titlePanel();
        JPanel centerPanel = createCenterPanel();
        add(titlePanel);
        container.add(inputPanel);
        add(centerPanel);

        container.add(outputPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Extracted method to create panel with buttons
    private JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
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


        exitButton = new JButton("  Exit  ");
        exitButton.setBackground(Color.RED);
        exitButton.addActionListener(this);
        centerPanel.add(exitButton);

        return centerPanel;
    }

    private JPanel titlePanel() {
        JPanel outerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBorder(BorderFactory.createTitledBorder("Start:"));

        JLabel titleLabel = new JLabel("Page Replacement");
        titleLabel.setFont(new Font("minecraft", Font.BOLD, 20));
        titleLabel.setForeground(Color.BLUE);

        JLabel help = new JLabel("<html>Bước 1: Nhập dữ liệu ~> chọn Add để lưu <br>Bước 2: Click vào thuật toán muốn dùng <br> </html>");
        help.setFont(new Font("minecraft", Font.BOLD, 16));
        help.setForeground(Color.DARK_GRAY);
        JLabel author = new JLabel("<html>  Author: Nguyễn Quang Thành <html>");
        author.setFont(new Font("minecraft", Font.BOLD, 16));
        author.setForeground(Color.green.darker());

        titlePanel.add(titleLabel);
        titlePanel.add(help);
        titlePanel.add(author);
        outerPanel.setBorder(BorderFactory.createTitledBorder("Hi:"));

        outerPanel.add(titlePanel, BorderLayout.WEST);

        return outerPanel;
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
            Output output = func_.sc(input);
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