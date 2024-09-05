package com.example.calciapp;

import android.view.View;
import android.widget.TextView;

public class OperationClickListener implements View.OnClickListener {
    private String operation;
    private MainActivity mainActivity;

    public OperationClickListener(String operation, MainActivity mainActivity) {
        this.operation=operation;
        this.mainActivity=mainActivity;
    }

    @Override
    public void onClick(View view) {
        if (!mainActivity.getCurrentno().equals("")) {
            if (!mainActivity.getOperation().equals("")) {
                double result = mainActivity.performOperation();
                mainActivity.getResult().setText(String.valueOf(result));
                mainActivity.setPreviousno(String.valueOf(result));
                mainActivity.setCurrentno("");
            } else {
                mainActivity.setPreviousno(mainActivity.getCurrentno());
                mainActivity.setCurrentno("");
            }
            mainActivity.setOperation(operation);
        }
    }
}
