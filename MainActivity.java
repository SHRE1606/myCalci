package com.example.mycalci;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    // Ids of all the number/digit buttons
    int[] numberButtonIds = { R.id.b0, R.id.b1, R.id.b2, R.id.b3,
            R.id.b4, R.id.b5, R.id.b6, R.id.b7,
            R.id.b8, R.id.b9};

    // Ids of all the operators
    int[] opButtonIds = { R.id.fun1, R.id.fun2, R.id.fun3, R.id.fun4, R.id.fun5, R.id.fun6, R.id.fun7, R.id.fun8 };

    Button numberButtons[] = new Button[10];    // An array of 10 number buttons
    Button opButtons[] = new Button[8];        // An array of 5 op buttons
    TextView resultBox;                         // The result text box
    Button clearButton;
    Button screenClearButton;


    // Variables for the functioning of the Calc
    double prevNum;         // Tells me the previous number
    float decimal;          // For decimal values in display-box
    String prevOp;          // Tells me the previous operator clicked
    boolean opClicked;      // Tells me if an operator was clicked


    // This is the onCreate() function we talked about. This is the function called when the
    // Activity is first loaded onto the screen.
    // !! REMEMBER: you can use findViewById() only once the Activity is loaded, hence, you can
    // initialize UI elements (Buttons and stuff) only after this function is called, or when this
    // function is called.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialise the variables
        prevNum = 0;
        prevOp = "=";           // Initially, assume '=' is the operator
        opClicked = false;      // Initially, we assume no operator is clicked


        // Initialize the clear and clear all
        clearButton = findViewById(R.id.fun2);
        screenClearButton = findViewById(R.id.fun1);

        // Initialize the TextView
        resultBox = findViewById(R.id.resultbox1);

        // Initialize all the number buttons
        for(int i = 0; i < 10; i++){
            // Assigning the button referenced by the id to the button variable (in the array)
            numberButtons[i] = findViewById(numberButtonIds[i]);

            // Setting the click listener on each button
            // numberListener is defined on Line 82
            numberButtons[i].setOnClickListener(numberListener);
        }

        //Initialize all the op buttons
        for (int i=2; i<8; i++)
        {
            opButtons[i] = findViewById(opButtonIds[i]);
            opButtons[i].setOnClickListener(opListener);
        }

        //Set a click listener for the clear button and screenclear button
        screenClearButton.setOnClickListener(new View.OnClickListener() {

            // Clearing all the data here
            @Override
            public void onClick(View v) {
                prevNum = 0;
                prevOp = "=";
                resultBox.setText("0");
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {

            // Clearing all the data here
            @Override
            public void onClick(View v) {
                {
                    if (prevNum == (int) prevNum)
                        prevNum /= 10;
                }
                prevOp = "=";
                resultBox.setText(Double.toString(prevNum));
            }
        });
    }

    View.OnClickListener numberListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String buttonText = ((Button) v) // Typecast the view into a Button
                    .getText().toString();

            // Convert the string into a number
            double numberClicked = Double.parseDouble(buttonText);

            // Do the following steps only if a numberButton was clicked earlier
            if (!opClicked) {
                // Get the number in the result box
                double numberInBox = Double.parseDouble(resultBox.getText().toString());
                numberInBox = numberInBox * 10 + numberClicked;
                resultBox.setText(Double.toString(numberInBox));
            }
            else{
                resultBox.setText(Double.toString(numberClicked));
            }

            opClicked = false; // A number was clicked now
        }
    };

    View.OnClickListener opListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Get the number in the result box
            double numberInBox = Double.parseDouble(resultBox.getText().toString());

            // Perform necessary operation depending on the operator clicked
            switch (prevOp){
                case "+":
                    prevNum += numberInBox;
                    break;

                case "-":
                    prevNum -= numberInBox;
                    break;

                case "x":
                    prevNum *= numberInBox;
                    break;

                case "/":
                    prevNum /= numberInBox;
                    break;

                case "=":
                    prevNum = numberInBox;
                    break;
                case ".":
                 /*   decimal = (float) prevNum;
                    prevNum = numberInBox + decimal/10;*/
                    break;
            }

            opClicked = true; // An operator was clicked now
            resultBox.setText(Double.toString(prevNum));
            prevOp = ((Button) v).getText().toString();
        }
    };
    public void complexCal (View view)
    {
      //  EditText editText = findViewById(R.id.resultbox1);
      //  Editable result = editText.getText();

        Intent intent1 = new Intent (this, Scientific_mode.class);
      //  intent1.putExtra("MAIN_RESULT", result);
        startActivity(intent1);
    }
}