package com.example.alec.positive_eating;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Launch_Screen extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_screen);

        Button customer = (Button) findViewById(R.id.Customer);
        Button employee = (Button) findViewById(R.id.Employee);
        customer.setOnClickListener(this);
        employee.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.Customer:
            //TODO
            break;
            case R.id.Employee:
                startActivity(new Intent(this, Employee_LoginWindow.class)); break;
            default: break;
        }
    }
}