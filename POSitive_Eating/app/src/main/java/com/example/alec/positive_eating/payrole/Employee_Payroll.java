/**
 * @author Alec
 */
package com.example.alec.positive_eating.payrole;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.ScrollView;

import com.example.alec.positive_eating.R;

import static com.example.alec.positive_eating.Singleton_ShaneConnect_Factory.getShaneConnect;

/**
 * This class will be used to payout the employees
 * It will set their hours, paid hours, and set counters to zero.
 */
public class Employee_Payroll extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payroll);
        ScrollView list = (ScrollView) findViewById(R.id.listOStuff);
        ConcreteListViewBuilder builder  = new ConcreteListViewBuilder(list,this);
        Director dir = new Director(getShaneConnect(),builder);
        dir.directDisplay();

    }
}
