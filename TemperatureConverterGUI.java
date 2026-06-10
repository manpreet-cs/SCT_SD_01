import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureConverterGUI extends JFrame implements ActionListener {

    JTextField tempField;
    JComboBox<String> unitBox;
    JTextArea resultArea;
    JButton convertButton, resetButton;

    public TemperatureConverterGUI() {

        setTitle("🌡 Temperature Converter");
        setSize(550, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        getContentPane().setBackground(new Color(248, 240, 255));
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 15));

        JLabel title = new JLabel("🌡 Temperature Converter");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(new Color(123, 104, 238));
        add(title);

        JLabel tempLabel = new JLabel("Temperature Value:");
        tempLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        add(tempLabel);

        tempField = new JTextField(15);
        tempField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        tempField.setBackground(new Color(230, 244, 255));
        add(tempField);

        JLabel unitLabel = new JLabel("Select Unit:");
        unitLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        add(unitLabel);

        String[] units = {"Celsius", "Fahrenheit", "Kelvin"};
        unitBox = new JComboBox<>(units);
        unitBox.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        unitBox.setBackground(new Color(255, 240, 245));
        add(unitBox);

        convertButton = new JButton("Convert");
        convertButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        convertButton.setBackground(new Color(186, 255, 201));
        convertButton.setFocusPainted(false);
        convertButton.addActionListener(this);
        add(convertButton);

        resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        resetButton.setBackground(new Color(255, 214, 224));
        resetButton.setFocusPainted(false);
        resetButton.addActionListener(this);
        add(resetButton);

        resultArea = new JTextArea(7, 30);
        resultArea.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        resultArea.setEditable(false);
        resultArea.setBackground(new Color(255, 251, 235));

        JScrollPane scrollPane = new JScrollPane(resultArea);
        add(scrollPane);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == convertButton) {

            try {

                double temp = Double.parseDouble(tempField.getText());

                String unit = (String) unitBox.getSelectedItem();

                resultArea.setText("");

                switch (unit.toLowerCase()) {

                    case "celsius":

                        double f = temp * 1.8 + 32;
                        double k = temp + 273.15;

                        resultArea.append(" Fahrenheit: " + String.format("%.2f  F", f) + "\n");
                        resultArea.append(" Kelvin: " + String.format("%.2f K", k));

                        break;

                    case "fahrenheit":

                        double c = (temp - 32) * 5 / 9;
                        double k2 = c + 273.15;

                        resultArea.append(" Celsius: " + String.format("%.2f C", c) + "\n");
                        resultArea.append(" Kelvin: " + String.format("%.2f K", k2));

                        break;

                    case "kelvin":

                        if (temp < 0) {

                            JOptionPane.showMessageDialog(
                                    this,
                                    "Kelvin cannot be negative!",
                                    "Invalid Input",
                                    JOptionPane.ERROR_MESSAGE
                            );

                            return;
                        }

                        double c2 = temp - 273.15;
                        double f2 = c2 * 1.8 + 32;

                        resultArea.append(" Celsius: " + String.format("%.2f C", c2) + "\n");
                        resultArea.append(" Fahrenheit: " + String.format("%.2f F", f2));

                        break;
                }

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a valid numeric value!",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }

        if (e.getSource() == resetButton) {

            tempField.setText("");
            resultArea.setText("");
            unitBox.setSelectedIndex(0);
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new TemperatureConverterGUI());
    }
}