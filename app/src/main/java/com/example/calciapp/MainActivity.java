package com.example.calciapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.BreakIterator;

public class MainActivity extends AppCompatActivity {

    private TextView result;
    private String currentno="";
    private String previousno="";
    private String operation="";
    private boolean isOperationpressed= false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result=findViewById(R.id.value1);

        int[] numbtn ={
                R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
                R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9,
        };

        View.OnClickListener numberClickListener =new View.OnClickListener(){
                public void onClick(View v) {
                    Button button = (Button) v;
                    if (currentno.length() < 8) {
                            currentno += button.getText().toString();
                            result.setText(currentno);
                    }
                }
        };
        for(int id:numbtn){
                findViewById(id).setOnClickListener(numberClickListener);
        }

            findViewById(R.id.btnadd).setOnClickListener(new OperationClickListener("+", this));
            findViewById(R.id.sub).setOnClickListener(new OperationClickListener("-", this));
            findViewById(R.id.div).setOnClickListener(new OperationClickListener("/",this));
            findViewById(R.id.mul).setOnClickListener(new OperationClickListener("*", this));

            findViewById(R.id.btneq).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!currentno.equals("")&&!previousno.equals("")){
                        double result1 = performOperation();
                        result.setText(String.valueOf(result1));
                        currentno=String.valueOf(result1);
                        previousno="" ;
                        operation="";
                    }

                }
            });
        findViewById(R.id.btnclr).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentno="";
                result.setText("0");
            }
        });
        findViewById(R.id.btnAC).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentno = "";
                previousno = "";
                operation = "";
                result.setText("0");
            }
        });
        findViewById(R.id.btnHash).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 if(!currentno.equals("")) {
                    int hashValue = computeHash(currentno);
                    currentno= String.valueOf(hashValue);
                    result.setText(currentno);

            }
        }


        });
        findViewById(R.id.decimal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!currentno.contains(".")){
                    currentno+=".";
                    result.setText(currentno);
                }
            }
        });
    }
    public void handleOperationPress(){
        if (!currentno.equals("")) {
            previousno = currentno;
            currentno = "";
            result.setText(previousno);
            isOperationpressed = true;
        }
    }
    double performOperation() {
        double result1=0;
        double prev=Double.parseDouble(previousno);
        double curr=Double.parseDouble(currentno);

        switch(operation){
                case "+":
                result1= prev+curr;
                break;
            case "-":
                result1= prev-curr;
                break;
                case "*":
                result1= prev*curr;
                break;
                case "/":
                    if(curr!=0) {
                        result1 = prev / curr;
                    }
                    else {
                        result.setText(("error"));
                        currentno="";
                        previousno="";
                        operation="";
                    }
                break;
        }
        return result1;
    }
    private int computeHash(String number) {
        int hash = 0;
        for (char c : number.toCharArray()) {
            if (Character.isDigit(c)) {
                hash += Character.getNumericValue(c);
            }
        }
        return hash;
    }
    public String getCurrentno() {
        return currentno;
    }

    public void setCurrentno(String currentno) {
        this.currentno = currentno;
    }

    public String getPreviousno() {
        return previousno;
    }

    public void setPreviousno(String previousno) {
        this.previousno = previousno;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public TextView getResult() {
        return result;
    }
}
