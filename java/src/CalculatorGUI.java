import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI {
    private JFrame frame;
    private JTextField textField;
    private double firstNumber;
    private String operator;
    private JLabel historyLabel;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                CalculatorGUI window = new CalculatorGUI();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public CalculatorGUI() {
        initialize();
    }

    private void initialize() {
        // Configuração da janela principal
        frame = new JFrame("Calculadora");
        frame.setBounds(100, 100, 350, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));

        // Campo de texto para exibir os números
        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 24));
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        textField.setEditable(false);  // Impede a edição manual
        frame.getContentPane().add(textField, BorderLayout.NORTH);
        textField.setColumns(10);

        // Label para exibir o histórico
        historyLabel = new JLabel();
        historyLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        historyLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        frame.getContentPane().add(historyLabel, BorderLayout.CENTER);

        // Painel de botões
        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.SOUTH);
        panel.setLayout(new GridLayout(5, 4, 5, 5));

        // Botões de números e operações
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            ".", "0", "=", "+",
            "C", "←", "√", "%"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.PLAIN, 20));
            panel.add(button);
            button.addActionListener(new ButtonClickListener());
        }
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equals("C")) {
                textField.setText("");
                firstNumber = 0;
                operator = "";
                historyLabel.setText("");
            } else if (command.equals("←")) {
                String currentText = textField.getText();
                if (currentText.length() > 0) {
                    textField.setText(currentText.substring(0, currentText.length() - 1));
                }
            } else if (command.equals("=")) {
                try {
                    double secondNumber = Double.parseDouble(textField.getText());
                    double result = calculateResult(secondNumber);
                    textField.setText(String.valueOf(result));
                    historyLabel.setText(firstNumber + " " + operator + " " + secondNumber + " = " + result);
                    firstNumber = result;
                    operator = "";
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Entrada inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else if (command.equals("√")) {
                try {
                    double number = Double.parseDouble(textField.getText());
                    if (number < 0) {
                        JOptionPane.showMessageDialog(frame, "Erro: Raiz quadrada de número negativo!", "Erro", JOptionPane.ERROR_MESSAGE);
                    } else {
                        double result = Math.sqrt(number);
                        textField.setText(String.valueOf(result));
                        historyLabel.setText("√" + number + " = " + result);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Entrada inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else if (command.equals("%")) {
                try {
                    double number = Double.parseDouble(textField.getText());
                    double result = number / 100;
                    textField.setText(String.valueOf(result));
                    historyLabel.setText(number + "% = " + result);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Entrada inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else if (command.equals("/") || command.equals("*") || command.equals("-") || command.equals("+")) {
                try {
                    firstNumber = Double.parseDouble(textField.getText());
                    operator = command;
                    textField.setText("");
                    historyLabel.setText(firstNumber + " " + operator);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Entrada inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                textField.setText(textField.getText() + command);
            }
        }

        private double calculateResult(double secondNumber) {
            double result = 0;
            switch (operator) {
                case "+":
                    result = firstNumber + secondNumber;
                    break;
                case "-":
                    result = firstNumber - secondNumber;
                    break;
                case "*":
                    result = firstNumber * secondNumber;
                    break;
                case "/":
                    if (secondNumber == 0) {
                        JOptionPane.showMessageDialog(frame, "Erro: Divisão por zero!", "Erro", JOptionPane.ERROR_MESSAGE);
                        return 0;
                    }
                    result = firstNumber / secondNumber;
                    break;
            }
            return result;
        }
    }
}