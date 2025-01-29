import tkinter as tk
import math

# Funções da calculadora
def press(key):
    current = entry.get()
    
    # Se o texto exibido for "Error", limpa a entrada ao pressionar qualquer tecla
    if current == "Error":
        entry.delete(0, tk.END)
    
    if key == 'C':
        entry.delete(0, tk.END)  # Limpa toda a entrada
    elif key == 'CE':
        # Limpar após o último operador (somente o que vem depois)
        operators = ['+', '-', 'X', '/', '%']
        for i in range(len(current)-1, -1, -1):
            if current[i] in operators:
                entry.delete(i+1, tk.END)  # Apaga a parte após o último operador
                break
        else:
            entry.delete(0, tk.END)  # Limpa toda a entrada se não houver operadores
    elif key == '=':
        try:
            current = current.replace('X', '*')
            
            # Verificar se a operação é de porcentagem
            if '%' in current:
                num, perc = current.split('%')
                result = float(num) * (float(perc) / 100)
            else:
                result = eval(current)  # Avalia a expressão
            entry.delete(0, tk.END)
            entry.insert(tk.END, result)  # Exibe o resultado
        except:
            entry.delete(0, tk.END)
            entry.insert(tk.END, "Error")  # Exibe erro se a expressão for inválida
    elif key == 'x²':
        try:
            result = float(current) ** 2  # Calcula o quadrado
            entry.delete(0, tk.END)
            entry.insert(tk.END, result)  # Exibe o resultado
        except:
            entry.delete(0, tk.END)
            entry.insert(tk.END, "Error")
    elif key == '1/x':
        try:
            result = 1 / float(current)  # Calcula o inverso
            entry.delete(0, tk.END)
            entry.insert(tk.END, result)  # Exibe o resultado
        except:
            entry.delete(0, tk.END)
            entry.insert(tk.END, "Error")
    elif key == '!':
        try:
            result = math.factorial(int(current))  # Calcula o fatorial
            entry.delete(0, tk.END)
            entry.insert(tk.END, result)  # Exibe o resultado
        except:
            entry.delete(0, tk.END)
            entry.insert(tk.END, "Error")
    elif key == '+/-':
        if current.startswith('-'):
            entry.delete(0)
        else:
            entry.insert(0, '-')  # Alterna entre positivo e negativo
    elif key == '⌫':  # Apagar um dígito
        entry.delete(len(current)-1, tk.END)
    else:
        entry.insert(tk.END, key)  # Insere o caractere pressionado na entrada

# Configuração da janela
window = tk.Tk()
window.title("Calculadora")

# Tela de entrada
entry = tk.Entry(window, width=16, font=("Arial", 24), borderwidth=2, relief="solid", justify="right")
entry.grid(row=0, column=0, columnspan=4)

# Botões da calculadora
buttons = [
    ["%", "CE", "C", "⌫"],
    ["1/x", "x²", "!", "/"],
    ["7", "8", "9", "X"],
    ["4", "5", "6", "-"],
    ["1", "2", "3", "+"],
    ["+/-", "0", ",", "="]
]

# Adicionar botões na interface
for i, row in enumerate(buttons):
    for j, btn in enumerate(row):
        button = tk.Button(window, text=btn, width=5, height=2, font=("Arial", 18),
                           command=lambda key=btn: press(key))
        button.grid(row=i+1, column=j)

# Iniciar a interface
window.mainloop()
