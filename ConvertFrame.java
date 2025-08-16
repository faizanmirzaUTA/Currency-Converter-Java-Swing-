import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ConvertFrame extends JFrame {
    private JTextField inputField, outputField;
    private JButton convertButton, clearButton, exitButton;
    private JRadioButton usdFrom, mxnFrom, eurFrom, usdTo, mxnTo, eurTo;
    private ButtonGroup fromGroup, toGroup;

    public ConvertFrame() {
        // Frame setup
        setTitle("Currency Conversion");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(12, 1)); // Adjusted for more rows

        // Bold font for UI elements
        Font boldFont = new Font("SansSerif", Font.BOLD, 12);

        // Menu setup
        setupMenu(boldFont);

        // "Convert From" section
        JLabel convertFromLabel = new JLabel("Convert from:");
        convertFromLabel.setFont(boldFont);
        add(convertFromLabel);
        JPanel iconFromPanel = createIconPanelFrom(boldFont);
        add(iconFromPanel);

        // Input field
        JLabel inputLabel = new JLabel("Enter Currency:");
        inputLabel.setFont(boldFont);
        add(inputLabel);
        inputField = new JTextField("0.0", 10);
        inputField.setFont(boldFont);
        add(inputField);

        // "Convert To" section
        JLabel convertToLabel = new JLabel("Convert to:");
        convertToLabel.setFont(boldFont);
        add(convertToLabel);
        JPanel iconToPanel = createIconPanelTo(boldFont);
        add(iconToPanel);

        // Output field
        JLabel outputLabel = new JLabel("Comparable Currency is:");
        outputLabel.setFont(boldFont);
        add(outputLabel);
        outputField = new JTextField();
        outputField.setEditable(false); // Make the output field read-only
        outputField.setBackground(Color.LIGHT_GRAY); // Set background color to light grey
        outputField.setFont(boldFont);
        add(outputField);

        // Buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        convertButton = new JButton("Convert");
        clearButton = new JButton("Clear");
        exitButton = new JButton("Exit");

        // Set bold font for buttons
        convertButton.setFont(boldFont);
        clearButton.setFont(boldFont);
        exitButton.setFont(boldFont);

        buttonPanel.add(convertButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(exitButton);
        add(buttonPanel);

        // Button and Menu Actions
        setupActions();

        setVisible(true);
    }

    private void setupMenu(Font boldFont) {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic('F'); // Shortcut for File menu
        fileMenu.setFont(boldFont);

        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.setMnemonic('A'); // Shortcut for About
        aboutItem.setFont(boldFont);
        aboutItem.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "Mohammad Mirza\nINSY 4305 - 003",
                "About", JOptionPane.INFORMATION_MESSAGE));

        JMenuItem convertItem = new JMenuItem("Convert");
        convertItem.setMnemonic('N'); // Shortcut for Convert
        convertItem.setFont(boldFont);
        convertItem.addActionListener(e -> performConversion()); // Calls the conversion logic

        JMenuItem clearItem = new JMenuItem("Clear");
        clearItem.setFont(boldFont);
        clearItem.addActionListener(e -> {
            inputField.setText("0.0");
            outputField.setText("");
        });

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setMnemonic('X'); // Shortcut for Exit
        exitItem.setFont(boldFont);
        exitItem.addActionListener(e -> confirmExit());

        // Adding items to File menu
        fileMenu.add(aboutItem);
        fileMenu.add(convertItem);
        fileMenu.add(clearItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
    }

    private JPanel createIconPanelFrom(Font boldFont) {
        // Creating icons and labels for "Convert From" section
        Icon dollar = new ImageIcon(getClass().getResource("dollar.jpg"));
        Icon peso = new ImageIcon(getClass().getResource("peso.jpg"));
        Icon euro = new ImageIcon(getClass().getResource("euro.jpg"));

        JLabel dollarLabelFrom = new JLabel(dollar);
        JLabel pesoLabelFrom = new JLabel(peso);
        JLabel euroLabelFrom = new JLabel(euro);

        usdFrom = new JRadioButton("US Dollar");
        mxnFrom = new JRadioButton("Mexican Peso");
        eurFrom = new JRadioButton("Euro");

        usdFrom.setFont(boldFont);
        mxnFrom.setFont(boldFont);
        eurFrom.setFont(boldFont);

        fromGroup = new ButtonGroup();
        fromGroup.add(usdFrom);
        fromGroup.add(mxnFrom);
        fromGroup.add(eurFrom);
        usdFrom.setSelected(true); // Default selection

        JPanel panel = new JPanel(new GridLayout(2, 3));
        panel.add(dollarLabelFrom);
        panel.add(pesoLabelFrom);
        panel.add(euroLabelFrom);
        panel.add(usdFrom);
        panel.add(mxnFrom);
        panel.add(eurFrom);

        return panel;
    }

    private JPanel createIconPanelTo(Font boldFont) {
        // Creating icons and labels for "Convert To" section
        Icon dollar = new ImageIcon(getClass().getResource("dollar.jpg"));
        Icon peso = new ImageIcon(getClass().getResource("peso.jpg"));
        Icon euro = new ImageIcon(getClass().getResource("euro.jpg"));

        JLabel dollarLabelTo = new JLabel(dollar);
        JLabel pesoLabelTo = new JLabel(peso);
        JLabel euroLabelTo = new JLabel(euro);

        usdTo = new JRadioButton("US Dollar");
        mxnTo = new JRadioButton("Mexican Peso");
        eurTo = new JRadioButton("Euro");

        usdTo.setFont(boldFont);
        mxnTo.setFont(boldFont);
        eurTo.setFont(boldFont);

        toGroup = new ButtonGroup();
        toGroup.add(usdTo);
        toGroup.add(mxnTo);
        toGroup.add(eurTo);
        mxnTo.setSelected(true); // Default selection

        JPanel panel = new JPanel(new GridLayout(2, 3));
        panel.add(dollarLabelTo);
        panel.add(pesoLabelTo);
        panel.add(euroLabelTo);
        panel.add(usdTo);
        panel.add(mxnTo);
        panel.add(eurTo);

        return panel;
    }

    private void setupActions() {
        convertButton.addActionListener(e -> performConversion());

        // Key listener for the input field to trigger conversion on Enter key
        inputField.addActionListener(e -> performConversion());

        clearButton.addActionListener(e -> {
            inputField.setText("0.0");
            outputField.setText("");
        });

        exitButton.addActionListener(e -> confirmExit());
    }

    private void confirmExit() {
        int response = JOptionPane.showConfirmDialog(this,
                "Are you sure?", "Confirmation window",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private void performConversion() {
        try {
            double amount = Double.parseDouble(inputField.getText());
            double convertedAmount = convertCurrency(amount);

            String fromCurrency = getSelectedCurrency(fromGroup, true);
            String toCurrency = getSelectedCurrency(toGroup, false);

            // Display result in a dialog with bold text and information icon
            String message = String.format("<html><b>%s to %s</b><br><b>%.2f</b> is equivalent to <b>%.2f</b></html>",
                    fromCurrency, toCurrency, amount, convertedAmount);

            JOptionPane.showMessageDialog(this, message, "Result", JOptionPane.INFORMATION_MESSAGE);

            outputField.setText(String.valueOf(convertedAmount));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String getSelectedCurrency(ButtonGroup group, boolean isFromGroup) {
        if (isFromGroup) {
            if (usdFrom.isSelected()) return "US Dollar";
            if (mxnFrom.isSelected()) return "Mexican Peso";
            if (eurFrom.isSelected()) return "Euro";
        } else {
            if (usdTo.isSelected()) return "US Dollar";
            if (mxnTo.isSelected()) return "Mexican Peso";
            if (eurTo.isSelected()) return "Euro";
        }
        return "Currency";
    }

    private double convertCurrency(double amount) {
        // Example conversion logic
        if (usdFrom.isSelected() && mxnTo.isSelected()) {
            return amount * 20.48; // Example conversion rate
        } else if (usdFrom.isSelected() && eurTo.isSelected()) {
            return amount * 0.94; // Example conversion rate
        } else if (eurFrom.isSelected() && usdTo.isSelected()) {
            return amount / 1.06; // Example conversion rate
        }
        // Add other conversions as needed
        return amount; // Fallback (no conversion)
    }
}
