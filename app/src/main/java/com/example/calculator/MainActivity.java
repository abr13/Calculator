package com.example.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {

    private EditText result, newNumber;
    private TextView displayOperation;
    private String pendingOperation = "=";
    private Double op1 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713\n");


        result = findViewById(R.id.editTextResult);
        newNumber = findViewById(R.id.editTextOperation);
        displayOperation = findViewById(R.id.textView);

        Button button0 = findViewById(R.id.buttonZero);
        Button button1 = findViewById(R.id.buttonOne);
        Button button2 = findViewById(R.id.buttonTwo);
        Button button3 = findViewById(R.id.buttonThree);
        Button button4 = findViewById(R.id.buttonFour);
        Button button5 = findViewById(R.id.buttonFive);
        Button button6 = findViewById(R.id.buttonSix);
        Button button7 = findViewById(R.id.buttonSeven);
        Button button8 = findViewById(R.id.buttonEight);
        Button button9 = findViewById(R.id.buttonNine);
        Button buttonDot = findViewById(R.id.buttonDot);

        Button buttonDivide = findViewById(R.id.buttonDivide);
        Button buttonMultiply = findViewById(R.id.buttonMultiply);
        Button buttonPlus = findViewById(R.id.buttonPlus);
        Button buttonMinus = findViewById(R.id.buttonMinus);
        Button buttonEquals = findViewById(R.id.buttonEquals);

        View.OnClickListener OnClickListener1 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                newNumber.append(b.getText().toString());
            }
        };
        button0.setOnClickListener(OnClickListener1);
        button1.setOnClickListener(OnClickListener1);
        button2.setOnClickListener(OnClickListener1);
        button3.setOnClickListener(OnClickListener1);
        button4.setOnClickListener(OnClickListener1);
        button5.setOnClickListener(OnClickListener1);
        button6.setOnClickListener(OnClickListener1);
        button7.setOnClickListener(OnClickListener1);
        button8.setOnClickListener(OnClickListener1);
        button9.setOnClickListener(OnClickListener1);
        buttonDot.setOnClickListener(OnClickListener1);

        View.OnClickListener myOnClickListener2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                String op = b.getText().toString();
                String value = newNumber.getText().toString();
                try {
                    Double dValue = Double.valueOf(value);
                    performOperation(dValue, op);
                } catch (NumberFormatException e) {
                    newNumber.setText("");
                }
                pendingOperation = op;
                displayOperation.setText(pendingOperation);

            }
        };
        buttonDivide.setOnClickListener(myOnClickListener2);
        buttonMultiply.setOnClickListener(myOnClickListener2);
        buttonPlus.setOnClickListener(myOnClickListener2);
        buttonMinus.setOnClickListener(myOnClickListener2);
        buttonEquals.setOnClickListener(myOnClickListener2);
    }

    private void performOperation(Double value, String operation) {
        if (op1 == null) {
            op1 = value;
        } else {
            if (pendingOperation.equals("=")) {
                pendingOperation = operation;

            }
            switch (pendingOperation) {
                case "=":
                    op1 = value;
                    break;
                case "/":
                    if (value == 0) {
                        op1 = 0.0;
                    } else {
                        op1 /= value;
                    }
                    break;
                case "*":
                    op1 *= value;
                    break;
                case "+":
                    op1 += value;
                    break;
                case "-":
                    op1 -= value;
                    break;
            }
        }
        result.setText(op1.toString());
        newNumber.setText("");

    }
}
