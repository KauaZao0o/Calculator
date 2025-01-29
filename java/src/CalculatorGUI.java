import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI {
    private JFrame frame;
    private JTextField textField;
    private double firstNumber;
    private String operator;

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
        frame = new JFrame();
        frame.setBounds(100, 100, 350, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));

        // Campo de texto para exibir os números
        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 24));
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        frame.getContentPane().add(textField, BorderLayout.NORTH);
        textField.setColumns(10);

        // Painel de botões
        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new GridLayout(5, 4, 5, 5));

        // Botões de números
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "X",  // Alterado de '*' para 'X'
            "1", "2", "3", "-",
            "0", ".", "=", "+",
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.PLAIN, 20));
            panel.add(button);
            button.addActionListener(new ButtonClickListener());
        }

        // Botão de limpar dentro do painel
        JButton clearButton = new JButton("C");
        clearButton.setFont(new Font("Arial", Font.PLAIN, 20));
        clearButton.addActionListener(e -> textField.setText(""));  // Ação para limpar o campo
        panel.add(clearButton);  // Adiciona o botão de limpar ao painel

        // Botão de Backspace com símbolo de seta
        JButton backspaceButton = new JButton("←");
        backspaceButton.setFont(new Font("Arial", Font.PLAIN, 20));
        backspaceButton.addActionListener(e -> {
            String currentText = textField.getText();
            if (currentText.length() > 0) {
                textField.setText(currentText.substring(0, currentText.length() - 1));
            }
        });
        panel.add(backspaceButton);  // Adiciona o botão de backspace ao painel
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.charAt(0) == 'C') {
                textField.setText("");
                firstNumber = 0;
                operator = "";
            } else if (command.charAt(0) == '=') {
                double secondNumber = Double.parseDouble(textField.getText());
                double result = 0;

                switch (operator) {
                    case "+":
                        result = firstNumber + secondNumber;
                        break;
                    case "-":
                        result = firstNumber - secondNumber;
                        break;
                    case "X":  // Alterado para 'X'
                        result = firstNumber * secondNumber;
                        break;
                    case "/":
                        if (secondNumber == 0) {
                            JOptionPane.showMessageDialog(frame, "Erro: Divisão por zero!", "Erro", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        result = firstNumber / secondNumber;
                        break;
                }

                textField.setText(String.valueOf(result));
                firstNumber = result;
                operator = "";
            } else if (command.equals("/") || command.equals("X") || command.equals("-") || command.equals("+")) {  // Alterado para 'X'
                firstNumber = Double.parseDouble(textField.getText());
                operator = command;
                textField.setText("");
            } else {
                textField.setText(textField.getText() + command);
            }
        }
    }
}
