import javax.swing.JOptionPane;

public class Calculator {
    public static void main(String[] args) {
        while (true) {
            try {
                // Solicita o primeiro número ao usuário
                String firstNumberInput = JOptionPane.showInputDialog(null, "Digite o primeiro número:", "Calculadora", JOptionPane.PLAIN_MESSAGE);
                if (firstNumberInput == null) break; // Encerra se o usuário cancelar
                double firstNumber = Double.parseDouble(firstNumberInput);

                // Solicita o segundo número ao usuário
                String secondNumberInput = JOptionPane.showInputDialog(null, "Digite o segundo número:", "Calculadora", JOptionPane.PLAIN_MESSAGE);
                if (secondNumberInput == null) break; // Encerra se o usuário cancelar
                double secondNumber = Double.parseDouble(secondNumberInput);

                // Solicita a operação ao usuário
                String[] options = {"Adição", "Subtração", "Multiplicação", "Divisão", "Sair"};
                int operation = JOptionPane.showOptionDialog(
                        null,
                        "Escolha a operação:",
                        "Calculadora",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        options,
                        options[0]
                );

                if (operation == 4 || operation == JOptionPane.CLOSED_OPTION) break; // Encerra se o usuário escolher "Sair"

                double result = 0;
                switch (operation) {
                    case 0: // Adição
                        result = firstNumber + secondNumber;
                        break;
                    case 1: // Subtração
                        result = firstNumber - secondNumber;
                        break;
                    case 2: // Multiplicação
                        result = firstNumber * secondNumber;
                        break;
                    case 3: // Divisão
                        if (secondNumber == 0) {
                            JOptionPane.showMessageDialog(null, "Erro: Divisão por zero!", "Erro", JOptionPane.ERROR_MESSAGE);
                            continue;
                        }
                        result = firstNumber / secondNumber;
                        break;
                }

                // Exibe o resultado
                JOptionPane.showMessageDialog(null, "O resultado é: " + result, "Resultado", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada inválida. Por favor, insira números válidos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Mensagem de despedida
        JOptionPane.showMessageDialog(null, "Calculadora encerrada.", "Encerrado", JOptionPane.INFORMATION_MESSAGE);
    }
}
