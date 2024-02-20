package ma.enset.app;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    BigDecimal value;
    BigDecimal resultValue;
    String operation;
    String currentOperation = "";

    TextView result;
    Button num1, num2, num3, num4, num5, num6, num7, num8, num9, num0;
    Button dot, plus, minus, multiply, divide, equal, clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.screen);
        num0 = findViewById(R.id.num0);
        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        num3 = findViewById(R.id.num3);
        num4 = findViewById(R.id.num4);
        num5 = findViewById(R.id.num5);
        num6 = findViewById(R.id.num6);
        num7 = findViewById(R.id.num7);
        num8 = findViewById(R.id.num8);
        num9 = findViewById(R.id.num9);
        dot = findViewById(R.id.dot);
        plus = findViewById(R.id.plus);
        minus = findViewById(R.id.minus);
        multiply = findViewById(R.id.multiply);
        divide = findViewById(R.id.divide);
        equal = findViewById(R.id.equal);
        clear = findViewById(R.id.clear);

        View.OnClickListener numberClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button clickedButton = (Button) v;
                String buttonText = clickedButton.getText().toString();
                currentOperation += buttonText;
                result.setText(currentOperation);
            }
        };

        num0.setOnClickListener(numberClick);
        num1.setOnClickListener(numberClick);
        num2.setOnClickListener(numberClick);
        num3.setOnClickListener(numberClick);
        num4.setOnClickListener(numberClick);
        num5.setOnClickListener(numberClick);
        num6.setOnClickListener(numberClick);
        num7.setOnClickListener(numberClick);
        num8.setOnClickListener(numberClick);
        num9.setOnClickListener(numberClick);
        dot.setOnClickListener(numberClick);

        View.OnClickListener operationClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button clickedButton = (Button) v;
                String buttonText = clickedButton.getText().toString();
                if (!result.getText().toString().isEmpty()) {
                    operation = buttonText;
                    value = new BigDecimal(result.getText().toString());
                    currentOperation += operation;
                    result.setText(currentOperation);
                }
            }
        };
        plus.setOnClickListener(operationClick);
        minus.setOnClickListener(operationClick);
        multiply.setOnClickListener(operationClick);
        divide.setOnClickListener(operationClick);

        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!currentOperation.isEmpty() && operation != null) {
                    int index = currentOperation.indexOf(operation);
                    if (index != -1) {
                        String firstOperand = currentOperation.substring(0, index);
                        String secondOperand = currentOperation.substring(index + 1);
                        BigDecimal firstValue = new BigDecimal(firstOperand);
                        BigDecimal secondValue = new BigDecimal(secondOperand);

                        switch (operation) {
                            case "+":
                                resultValue = firstValue.add(secondValue);
                                break;
                            case "-":
                                resultValue = firstValue.subtract(secondValue);
                                break;
                            case "X":
                                resultValue = firstValue.multiply(secondValue);
                                break;
                            case "/":
                                if (secondValue.compareTo(BigDecimal.ZERO) != 0) {
                                    resultValue = firstValue.divide(secondValue, 10, BigDecimal.ROUND_HALF_UP);
                                } else {
                                    resultValue = BigDecimal.ZERO;
                                }
                                break;
                        }
                        result.setText(resultValue.stripTrailingZeros().toPlainString());
                        currentOperation = resultValue.toPlainString();
                        operation = null;
                    } else {
                        // Handle the case when the operation is not found in the currentOperation
                        result.setText("Error");
                        currentOperation = "";
                        operation = null;
                    }
                } else {
                    // Handle the case when there is no operation to perform
                    result.setText("0");
                }
            }
        });



        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operation = null;
                value = null;
                resultValue = null;
                result.setText("");
                currentOperation = "";
            }
        });
    }
}
