package com.example.mycalci;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Scientific_mode extends AppCompatActivity {

    // Ids of all the number/digit buttons
    int[] numberButtonIds = { R.id.b0, R.id.b1, R.id.b2, R.id.b3,
            R.id.b4, R.id.b5, R.id.b6, R.id.b7,
            R.id.b8, R.id.b9};

    // Ids of all the operators
    int[] opButtonIds = { R.id.fun1, R.id.fun2, R.id.fun3, R.id.fun4, R.id.fun5, R.id.fun6, R.id.fun7, R.id.fun8 };

    Button numberButtons[] = new Button[10];    // An array of 10 number buttons
    Button opButtons[] = new Button[8];        // An array of 5 op buttons
    TextView resultBox;                         // The result text box


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
        setContentView(R.layout.activity_scientific_mode);

        //Initialise the variables
        prevNum = 0;
        prevOp = "=";           // Initially, assume '=' is the operator
        opClicked = false;      // Initially, we assume no operator is clicked

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
        for (int i=0; i<8; i++)
        {
            opButtons[i] = findViewById(opButtonIds[i]);
            opButtons[i].setOnClickListener(opListener);
        }
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

    public double sin_fun(double p)
    {
        double a = p, b = p;
        for (int i=1; i<=10; i++)
        {
            p = p * (-1)*b*b/((2*i)*(2*i+1));
            a += p;
        }
        return a;
    }

    public double cos_fun(double p)
    {
        double a = 1, b = p;
        for (int i=1; i<=10; i++)
        {
            p = p* (-1)*b*b/((2*i-1)*(2*i));
            a += p;
        }
        return a;
    }

    public double tan_fun(double p)
    {
        double a;
        a = sin_fun(p)/cos_fun(p);
        return a;
    }

    public double ln_fun(double p)
    {
        p -= 1;
        double a = p, b = p;
        for (int i=1; i<=10; i++)
        {
            p = p * b*i/(i+1);
            a += p;
        }
        return a;
    }

    public double exponent_fun(double p)
    {
        double a = 1, b = p;
        for (int i=1; i<=10; i++)
        {
            p = p * b/i;
            a += p;
        }
        return a;
    }
    View.OnClickListener opListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Get the number in the result box
            double numberInBox = Double.parseDouble(resultBox.getText().toString());

            // Perform necessary operation depending on the operator clicked
            switch (prevOp){
                case "sin":
                    prevNum = sin_fun(numberInBox);
                    break;

                case "cos":
                    prevNum = cos_fun(numberInBox);
                    break;

                case "tan":
                    prevNum = tan_fun(numberInBox);
                    break;

                case "1/x":
                    prevNum = 1 / (float)prevNum;
                    break;

                case "lnx":
                    prevNum = ln_fun( numberInBox);
                    break;

                case "e^x":
                    prevNum = exponent_fun(numberInBox);
                    break;
            }

            opClicked = true; // An operator was clicked now
            resultBox.setText(Double.toString(prevNum));
            prevOp = ((Button) v).getText().toString();
        }
    };

    public void basicCal (View view)
    {
     //   EditText editText = findViewById(R.id.resultbox1);
     //   Editable result = editText.getText();

        Intent intent2 = new Intent (this, MainActivity.class);
     //   intent2.putExtra("MAIN_RESULT", result);
        startActivity(intent2);
    }
}
