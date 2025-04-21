import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame implements ActionListener {

    private JTextField inputField;
    private JComboBox<String> unitComboBox;
    private JTextArea resultArea;
    private JButton convertButton;

    public Main() {
        setTitle("Temperature Converter");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        add(new JLabel("Enter Temperature:"));
        inputField = new JTextField(10);
        add(inputField);

        String[] units = {"Celsius", "Fahrenheit", "Kelvin"};
        unitComboBox = new JComboBox<>(units);
        add(unitComboBox);

        convertButton = new JButton("Convert");
        convertButton.addActionListener(this);
        add(convertButton);

        resultArea = new JTextArea(6, 30);
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea));

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double inputTemp = Double.parseDouble(inputField.getText());
            String unit = (String) unitComboBox.getSelectedItem();
            String result = convertTemperature(inputTemp, unit);
            resultArea.setText(result);
        } catch (NumberFormatException ex) {
            resultArea.setText("Please enter a valid number.");
        }
    }

    private String convertTemperature(double temp, String unit) {
        double celsius = 0, fahrenheit = 0, kelvin = 0;

        switch (unit) {
            case "Celsius":
                celsius = temp;
                fahrenheit = (temp * 9/5) + 32;
                kelvin = temp + 273.15;
                break;
            case "Fahrenheit":
                celsius = (temp - 32) * 5/9;
                fahrenheit = temp;
                kelvin = (temp - 32) * 5/9 + 273.15;
                break;
            case "Kelvin":
                celsius = temp - 273.15;
                fahrenheit = (temp - 273.15) * 9/5 + 32;
                kelvin = temp;
                break;
        }

        return String.format(
                "Celsius: %.2f °C\nFahrenheit: %.2f °F\nKelvin: %.2f K",
                celsius, fahrenheit, kelvin
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main());
    }
}
