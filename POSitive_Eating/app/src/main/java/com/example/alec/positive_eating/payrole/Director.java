package com.example.alec.positive_eating.payrole;

import android.widget.Toast;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

import shaneconnect.Command;
import shaneconnect.ConcreteCommand;
import shaneconnect.DeleteDecorator;
import shaneconnect.DeleteLogs;
import shaneconnect.ShaneConnect;
import shaneconnect.ShaneConnectEmployee;

/**
 * Created by shane on 4/11/17.
 */

public class Director {

    private ShaneConnect connection;

    boolean doneLoading;

    private Object sync;

    private HashMap<String,Employee_Payment> hash;

    ArrayList<Employee_Payment> employees;

    private ConcreteListViewBuilder builder;

    public Director(ShaneConnect shane, ConcreteListViewBuilder bui){
        employees = new ArrayList<Employee_Payment>();
        this.connection = shane;
        this.builder = bui;
        sync = new Object();
        hash = new HashMap<String,Employee_Payment>();
        doneLoading = false;
    }

    public void directDisplay(){
        getLogs();

    }

    private void getLogs(){
        getLogs(0);
    }

    private void getLogs(final int index){

        connection.getlogs(index, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(!response.has("none")){
                        int employeeid = response.getInt("EMPLOYEE_ID");
                        int time = response.getInt("TIME");
                        searchEmployee(employeeid,time,index + 1);

                    }else{
                        for(int x=0;x<employees.size();x++){
                            builder.buildPart(employees.get(x));

                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void searchEmployee(final int id, final int time, final int index){

        connection.getEmployeeByID(id, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                   String first = (String) response.get("first");
                   String last = (String) response.get("last");
                    int payrt = response.getInt("rate");
                    Employee_Payment employee = new Employee_Payment(first,last,id,payrt);
                    if(hash.get(employee.getUserName()) == null){
                        hash.put(employee.getUserName(),employee);
                        employees.add(employee);
                    }
                    hash.get(employee.getUserName()).addTime(time);
                    getLogs(index + 1);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public void deleteEmployeeLogs(){
        builder.clear();
        Command command = new ConcreteCommand();
        for(int x=0;x<employees.size();x++){
            DeleteLogs com = new DeleteLogs(connection,employees.get(x),command);
            command = com;
        }
        command.exectute(new Response.Listener<JSONObject>(){

            @Override
            public void onResponse(JSONObject response) {}
        });
    }

}
