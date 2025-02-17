let currentInput = "";
let firstNumber = "";
let operator = "";
let history = "";

function appendNumber(number) {
    currentInput += number;
    updateDisplay();
}

function appendOperator(op) {
    if (currentInput === "") return;
    if (firstNumber !== "") calculateResult();
    operator = op;
    firstNumber = currentInput;
    history = `${currentInput} ${operator}`;
    currentInput = "";
    updateDisplay();
}

function calculateResult() {
    if (currentInput === "" || firstNumber === "") return;
    const num1 = parseFloat(firstNumber);
    const num2 = parseFloat(currentInput);
    let result = 0;

    switch (operator) {
        case "+":
            result = num1 + num2;
            break;
        case "-":
            result = num1 - num2;
            break;
        case "*":
            result = num1 * num2;
            break;
        case "/":
            if (num2 === 0) {
                alert("Erro: Divisão por zero!");
                return;
            }
            result = num1 / num2;
            break;
        case "%":
            result = num1 % num2;
            break;
    }

    history = `${firstNumber} ${operator} ${currentInput} = ${result}`;
    currentInput = result.toString();
    firstNumber = "";
    operator = "";
    updateDisplay();
}

function clearDisplay() {
    currentInput = "";
    firstNumber = "";
    operator = "";
    history = "";
    updateDisplay();
}

function backspace() {
    currentInput = currentInput.slice(0, -1);
    updateDisplay();
}

function updateDisplay() {
    document.getElementById("result").value = currentInput;
    document.getElementById("history").innerText = history;
}