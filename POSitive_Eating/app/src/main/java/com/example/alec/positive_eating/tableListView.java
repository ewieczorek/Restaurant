package com.example.alec.positive_eating;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.example.alec.positive_eating.Singleton_Employee_List.getListInstance;
import static com.example.alec.positive_eating.Singleton_ShaneConnect_Factory.getShaneConnect;
import static com.example.alec.positive_eating.Singleton_Table_List.getTableListInstance;

public class tableListView extends AppCompatActivity {
    private int index;
    ViewGroup mRootLayout;
    LinearLayout listView;
    private List<Table> allTheTables = new ArrayList<>();
    private List<employee> employeeList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        employeeList = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_list_view);
        //allTheTables = (ArrayList<Table>) getIntent().getSerializableExtra("allthetables");
        mRootLayout = (RelativeLayout) findViewById(R.id.activity_table_list_view);
        ScrollView scroll = new ScrollView(this);

        listView = new LinearLayout(this);
        listView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        listView.setOrientation(LinearLayout.VERTICAL);
        mRootLayout.addView(scroll);
        scroll.addView(listView);

        shaneconnect.ShaneConnect vista = getShaneConnect();
        index = 0;
        allTheTables = getTableListInstance().getTList();
        employeeList = getListInstance().getEList();

        Iterator<Table> tableIterator = allTheTables.iterator();
        while(tableIterator.hasNext()){
            Table temp = tableIterator.next();
            temp.setContext(tableListView.this);
            temp.setRootLayout(listView);
            temp.addListItem(listView);
        }

    }

    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(tableListView.this, tableMap.class);
        this.finishActivity(0);
        tableListView.this.startActivity(myIntent);
    }

//    public void retrieveTables(final int index, final shaneconnect.ShaneConnect s) {
//        s.getTables(index, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                try{
//                    //Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
//                    //// TODO
//                    Table temp = new Table(response.getString("name"), response.getInt("x_coord"), response.getInt("y_coord"), response.getInt("status"), response.getInt("employee_id"), " ", response.getInt("number_seats"), employeeList, tableListView.this, listView);
//                    allTheTables.add(temp);
//                    temp.addListItem(listView);
//                    retrieveTables(index+1,s);
//                } catch (JSONException e) {
//                    return;
//                }
//            }
//
//        });
//    }
//
//    private void getEmployeeList(final int index, final shaneconnect.ShaneConnect s) {
//        s.getEmployees(index, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                try {
//                    employee temp = new employee(response.getString("first"), response.getString("last"), response.getInt("emp_id"));
//                    employeeList.add(temp);
//                    String temp2 = temp.getLast() + ", " + temp.getFirst();
//                    getEmployeeList(index + 1, s);
//                } catch (JSONException e) {
//                    retrieveTables(0, s);
//                    return;
//                }
//            }
//
//        });
//    }
}
