package shaneconnect;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Console;
import java.util.ArrayList;

/**
 * The latest in Shane Drafahl Industries® has led to the development in a way to modulate your server needs into one convenient way to communicate with
 * a server you own.
 */
public class ShaneConnect {


    /**
     * String address of where the server
     * and example might be
     */
    private String url;

    /**
     * maind is what stores the mainactivity that can put on operation in a query for the OS
     */
    private Context maind;

    /**
     *
     * @param url is a String object that holds the location of the server you want to connect to and example may be http://10.0.2.2:3019 do not enter the address of the post request
     * @param d is the MainActivity that controls the runtime of the app, for example to give an argument of this from the MainActivity class is just to type "this".
     */
    public ShaneConnect(String url, Context d) {
        this.url=url;
        maind=d;
    }

    /**
     * getAccountData will make a call to the server to get a json object of the representing account user given as an argument.
     * @param account_name this is the name of the account you want data from, for example "SMITH_BOB_1" would be an argument. The convention is that the last name, first name, and a unique ID are concatenated together with underscores to create the username.
     * @param s is a asynchronous callback function that responds with a json object of the form {last:String,first:String,emp_id:int,perm_string:String,address:String,cell:String,cell:String,status:int,pass:String,rate:int,routing:String,social:String,bank_num:String}
     */
    public void getAccountData(String account_name,Response.Listener<JSONObject> s ) {
        RequestQueue queue = Volley.newRequestQueue(maind);
        JSONObject out = new JSONObject();
        try {
            out.put("name",account_name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest lastFMAuthRequest = new JsonObjectRequest(Request.Method.POST, url + "/getAccount", out, s, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.print(error.networkResponse);

            }

        });
        queue.add(lastFMAuthRequest);
    }

    /**
     *This creates a user on the EMPLOYEES database and returns the username for the new employee
     * @param lname the last name
     * @param fname the first name
     * @param permissionString a string of characters that correspond to permissions such as 1111
     * @param status could be used for different levels of the company. For example 2 might be manager and 0 be super user
     * @param password a password
     * @param address the address of the employee
     * @param cell the cellphone number
     * @param phone their primary phone number
     *
     */
    public void createAccount(String lname,String fname,String permissionString,String routingNumber,String social,String bankNumber,int status,String password,String address,String cell, String phone,int hourlyRate,Response.Listener<JSONObject> s){
        RequestQueue queue = Volley.newRequestQueue(maind);
        JSONObject out = new JSONObject();
        try{
            out.put("lnam",lname);
            out.put("fname",fname);
            out.put("perm", permissionString);
            out.put("stat",status);
            out.put("pass",password);
            out.put("address", address);
            out.put("cell",cell);
            out.put("phone",phone);
            out.put("rate",hourlyRate);
            out.put("routing",routingNumber);
            out.put("social",social);
            out.put("bank_num",bankNumber);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest lastFMAuthRequest = new JsonObjectRequest(Request.Method.POST, url + "/createAccount", out, s, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.print(error.networkResponse);

            }

        });
        queue.add(lastFMAuthRequest);
    }

    /**
     * Gets employee information with an id number
     * @param id
     * @param s the response method returns a json of the form given when searching for a
     *          employee by index
     */
    public void getEmployeeByID(int id,Response.Listener<JSONObject> s ){
        RequestQueue queue = Volley.newRequestQueue(maind);
        JSONObject out = new JSONObject();
        try{
            out.put("id",id);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest lastFMAuthRequest = new JsonObjectRequest(Request.Method.POST, url + "/getEmployeeWithID", out, s, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.print(error.networkResponse);

            }

        });
        queue.add(lastFMAuthRequest);
    }

    /**
     * Get food by index is a way to recursively return all food items.
     * @param index the index number for the food item
     * @param s is a asynchronous callback function that responds with a json object of the form {name:String,food_id:int,quantity:int,price:int,desc:string,options:string}
     */
    public void getFoodByIndex(int index,Response.Listener<JSONObject> s){
        RequestQueue queue = Volley.newRequestQueue(maind);
        JSONObject out = new JSONObject();
        try {
            out.put("index",index);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest lastFMAuthRequest = new JsonObjectRequest(Request.Method.POST, url + "/getFoodByIndex", out, s, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.print(error.networkResponse);

            }

        });
        queue.add(lastFMAuthRequest);
    }

    public void dontUseThis(){
        while(true){
            System.out.print("told you not to use this");
        }
    }

    /**
     *
     * @param index index of employee, enter 0 and use recursively if you want to get all employees
     * @param s returns json object {last:String,first:String,emp_id:int,perm_string:String,address:String,cell:String,cell:String,status:int,pass:String,rate:int,routing:String,social:String,bank_num:String}
     *          or is null if the index does not exist
     */
    public void getEmployees(int index,Response.Listener<JSONObject> s){
        RequestQueue queue = Volley.newRequestQueue(maind);
        JSONObject out = new JSONObject();
        try {
            out.put("index",index);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest lastFMAuthRequest = new JsonObjectRequest(Request.Method.POST, url + "/getUserByIndex", out, s, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.print(error.networkResponse);

            }

        });
        queue.add(lastFMAuthRequest);
    }


    /**
     *
     * @param user the user name such as Smith_Bob_1
     * @param isClockingIn enter 1 if clocking in, 0 if clocking out
     * @param s returns json object in the form in the response {error:int} if the error is 0 then is was added to the db
     */
    public void newEmployeeLog(String user,int isClockingIn,Response.Listener<JSONObject> s){
        RequestQueue queue = Volley.newRequestQueue(maind);
        JSONObject out = new JSONObject();
        try{
            out.put("user",user);
            out.put("isIn",isClockingIn);
            int temp;
            if(isClockingIn == 1){
                temp=0;
            }else{
                temp=1;
            }
            out.put("isOut",temp);
            out.put("timeStamp",((Number)System.currentTimeMillis()).intValue());
        } catch (JSONException e){
            e.printStackTrace();
        }
        JsonObjectRequest lastFMAuthRequest = new JsonObjectRequest(Request.Method.POST, url + "/log", out, s, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.print(error.networkResponse);

            }

        });
        queue.add(lastFMAuthRequest);
    }

    /**
     * Unstable as of this release
     * @param index
     * @param s
     */
    public void getlogs(final int index, Response.Listener<JSONObject> s){
        RequestQueue queue = Volley.newRequestQueue(maind);
        JSONObject out = new JSONObject();
        try{
            out.put("index",index);
        }catch(JSONException e){
            e.printStackTrace();
        }
        JsonObjectRequest lastFMAuthRequest = new JsonObjectRequest(Request.Method.POST, url + "/getLog", out,s , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.print(error.networkResponse);

            }

        });
        queue.add(lastFMAuthRequest);

    }

    /**
     * The response should provide a json object in the form {name:String,food_id:int,quantity: int,price:int,desc:String} returns null if nothing found.
     * @param name of food
     * @param s response listenter that the response json object is in the form {name:String,food_id:String,quantity:int,price:int,desc:String,opt}
     */
    public void getFood(String name,Response.Listener<JSONObject> s){
        RequestQueue queue = Volley.newRequestQueue(maind);
        JSONObject out = new JSONObject();
        try{
            out.put("name",name);
        }catch (JSONException e){
            e.printStackTrace();
        }
        JsonObjectRequest lastFMAuthRequest = new JsonObjectRequest(Request.Method.POST, url + "/getFood", out,s , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.print(error.networkResponse);

            }

        });
        queue.add(lastFMAuthRequest);
    }

    /**
     * Way to add food, if the database already has a food item of the same name it will update the quantitiy and price but if it does not exist it will make a new entry.
     * @param name name of food item.
     * @param price price of the food item.
     * @param desc description of food item.
     * @param quan the quantity of items
     * @param s call back method response will be null if did not work
     */
    public void addFood(String name,int price,String desc,int quan,ArrayList<String> options, Response.Listener<JSONObject> s ){
        RequestQueue queue = Volley.newRequestQueue(maind);
        JSONObject out = new JSONObject();
        String opt = optionParser(options);

        try{
            out.put("name",name);
            out.put("quan", quan);
            out.put("price", price);
            out.put("desc", desc);
            out.put("opt",opt);
        }catch (JSONException e){
            e.printStackTrace();
        }
        JsonObjectRequest lastFMAuthRequest = new JsonObjectRequest(Request.Method.POST, url + "/addFood", out,s , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.print(error.networkResponse);

            }

        });
        queue.add(lastFMAuthRequest);
    }
    private String optionParser(ArrayList<String> options){
        String result = "";
        for(int x=0;x<options.size();x++){
            result+=options.get(x);
            result+="-";
        }
        return result;
    }

    /**
     * Method used to place orders
     * @param desc description of the order
     * @param comp a list of food items used in this order for example if you ordered a hamburger and there was a hamburger in the food table you would do list.add("hamburger").
     * @param local A string that holds the location or where the meal will go. For example table 9
     * @param options An Arraylist of options that will correspond to the food items by index
     * @param s response method that responds with a json object in the form of {success:1} but if it failed to do it wont return anything and be null.
     */
    public void placeOrder(String desc,ArrayList<String> comp,ArrayList<String> options,int price, String local, Response.Listener<JSONObject> s ){
        String par = "";
        placeOrder(0,price,desc,comp,options,local,par,s);
    }

    private void placeOrder(final int index, final int price, final String desc, final ArrayList<String> list, final ArrayList<String> options, final String local, final String parsed , final Response.Listener<JSONObject> s ){
        if(index==list.size()){
            RequestQueue queue = Volley.newRequestQueue(maind);
            JSONObject out = new JSONObject();
            try {
                out.put("desc",desc);
                out.put("components", parsed);
                out.put("local", local);
                out.put("price",price);
            }catch (JSONException e){
                e.printStackTrace();
            }

            JsonObjectRequest lastFMAuthRequest = new JsonObjectRequest(Request.Method.POST, url + "/placeOrder", out ,s , new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.print(error.networkResponse);

                }
            });

            queue.add(lastFMAuthRequest);
        }else{
            getFood(list.get(index), new Response.Listener<JSONObject>(){
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        String c = "" + response.get("food_id");
                        String newParsed = parsed + c;
                        newParsed+= "(";
                        newParsed+=options.get(index);
                        newParsed+=")";
                        newParsed += "-";
                        placeOrder(index+1,price,desc,list,options,local,newParsed,s);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }



    /**
     * Gets orders, Response.Listener must be used recursively to call this function. If Response is empty then that means there are no more orders.
     * @param index this should be 0 and then recursively add index+1
     * @param s event response, sends JSON in the form {desc:String,order_id:int,componentString:String,local:String,price:int}. local is the location either being a table or an address.
     * componentString should be in the form 1(rare)-6(extra fries)
     */
    public void getOrders(int index,final Response.Listener<JSONObject> s){
        RequestQueue queue = Volley.newRequestQueue(maind);
        JSONObject out = new JSONObject();
        try{
            out.put("index",index);
        }catch (JSONException e){
            e.printStackTrace();
        }

        JsonObjectRequest lastFMAuthRequest = new JsonObjectRequest(Request.Method.POST, url + "/getOrder", out,s , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.print(error.networkResponse);

            }

        });
        queue.add(lastFMAuthRequest);
    }

    /**
     * Method to get all the food in a given id string. May be useful when working with getting orders.
     * @param foodIDS a string of food orders in the form x(option1)-x(option2)-x(option3- . The format given from calling getOrders should work when using the componentString from the given JSON object.
     * @param s response method, the response json object is in the format {NAME(ID):String,FOOD_ID(ID):int,QUANTITY(ID):int,PRICE(ID):int,DESCR(ID):String,OPTIONS(ID):jsonobject}, the OPTIONS(ID):jsonobject has the formate
     * {options + index:String....,options + index+1,:String},        Where (ID) is the concatenation of DESCR + ID for example.
     * Here is some sample code on how to use this to get all orders
     *          public void test(final int index, final ShaneConnect s, final TextView v) {
    s.getOrders(index, new Response.Listener<JSONObject>() {
    @Override
    public void onResponse(JSONObject response) {
    if(response.length()>0){
    v.setText(response.toString() + v.getText());
    test(index+1,s,v);
    }
    }

    });
    }
     *
     *          for index you will want to enter an argument 0
     */
    public void getFoodByID(String foodIDS,final Response.Listener<JSONObject> s){
        RequestQueue queue = Volley.newRequestQueue(maind);
        JSONObject out = new JSONObject();
        try{
            out.put("food",foodIDS);
        }catch (JSONException e){
            e.printStackTrace();
        }
        JsonObjectRequest lastFMAuthRequest = new JsonObjectRequest(Request.Method.POST, url + "/getFoodByIDString", out,s , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.print(error.networkResponse);

            }

        });
        queue.add(lastFMAuthRequest);
    }

    /**
     * Gets a table with an index. This is used to get all tables because it can be used recursively similarly to the example
     * given for getFoodByID
     * @param index the index of each table
     * @param s response function, response will be in the form {name:String,id:int,x_coord:int,y_coord:int, number_seats:int,status:int} or if requesting
     *          an index that does not exist response will be in the form {done:1}
     */
    public void getTables(int index,Response.Listener<JSONObject> s){
        RequestQueue queue = Volley.newRequestQueue(maind);
        JSONObject out = new JSONObject();
        try{
            out.put("index", index);
        }catch (JSONException e){
            e.printStackTrace();
        }
        JsonObjectRequest lastFMAuthRequest = new JsonObjectRequest(Request.Method.POST, url + "/getTable", out,s , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.print(error.networkResponse);

            }

        });
        queue.add(lastFMAuthRequest);
    }

    /**
     * Gets table information given a index
     * @param index the index
     * @param s will return response object of the form {name:String,id:int,x_coord:int,y_coord:int,number_seats:int,status:int}
     */
    public void getTableByIndex(int index,Response.Listener<JSONObject> s){
        RequestQueue queue = Volley.newRequestQueue(maind);
        JSONObject out = new JSONObject();
        try {
            out.put("index",index);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest lastFMAuthRequest = new JsonObjectRequest(Request.Method.POST, url + "/getTable", out,s , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.print(error.networkResponse);

            }

        });
        queue.add(lastFMAuthRequest);

    }

    /**
     * Method to add a customer to the database
     * @param user Their username, this could be anything including the email address
     * @param email Their email,
     * @param password the password to login for the user
     * @param s will return response object of the form {success:int} and 1 if it had a success
     */
    public void addCustomer(String user,String email,String password,Response.Listener<JSONObject> s){
        RequestQueue queue = Volley.newRequestQueue(maind);
        JSONObject out = new JSONObject();
        try {
            out.put("user", user);
            out.put("email",email);
            out.put("pass", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest lastFMAuthRequest = new JsonObjectRequest(Request.Method.POST, url + "/addCustomer", out,s , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.print(error.networkResponse);

            }

        });
        queue.add(lastFMAuthRequest);

    }

    /**
     *
     * @param user The username you are searching for
     * @param s will return response object of the form {user:string,id:int,email:string, pass:String}
     */
    public void getCustomer(String user,Response.Listener<JSONObject> s){
        RequestQueue queue = Volley.newRequestQueue(maind);
        JSONObject out = new JSONObject();
        try {
            out.put("user", user);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest lastFMAuthRequest = new JsonObjectRequest(Request.Method.POST, url + "/getCustomer", out,s , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.print(error.networkResponse);

            }

        });
        queue.add(lastFMAuthRequest);

    }

    /**
     * Used to add a table to the database.
     * @param name the name of the table
     * @param x the x coordinate
     * @param y the y coordinate
     * @param seats number of seats for the table
     * @param employeeID the id number of an employee
     * @param s the response method that has a response in the form {success:1} if it was able to create a table
     */
    public void setTable(String name,int x,int y,int seats,int status,int employeeID,Response.Listener<JSONObject> s){
        RequestQueue queue = Volley.newRequestQueue(maind);
        JSONObject out = new JSONObject();
        try{
            out.put("name",name);
            out.put("xcoord",x);
            out.put("ycoord",y);
            out.put("number_of_seats",seats);
            out.put("stat",status);
            out.put("employeeID", employeeID);
        }catch (JSONException e){
            e.printStackTrace();
        }
        JsonObjectRequest lastFMAuthRequest = new JsonObjectRequest(Request.Method.POST, url + "/setTable", out,s , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.print(error.networkResponse);

            }

        });
        queue.add(lastFMAuthRequest);
    }

    protected void placeReservation(String desc, int tableID,int status,int customerID,Response.Listener<JSONObject> s){
        RequestQueue queue = Volley.newRequestQueue(maind);
        JSONObject out = new JSONObject();
        try {
            out.put("desc", desc);
            out.put("table_id",tableID);
            out.put("status",status);
            out.put("customerID",customerID );
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest lastFMAuthRequest = new JsonObjectRequest(Request.Method.POST, url + "/placeReservations", out,s , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.print(error.networkResponse);

            }

        });
        queue.add(lastFMAuthRequest);

    }

    /**
     * This method removes a order from the database given a description
     * @param desc The description name of the order that will be removed
     * @param s response method, doesnt do anything but needs to
     */
    public void removeOrder(String desc,Response.Listener<JSONObject> s){
        RequestQueue queue = Volley.newRequestQueue(maind);
        JSONObject out = new JSONObject();
        try {
            out.put("desc", desc);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest lastFMAuthRequest = new JsonObjectRequest(Request.Method.POST, url + "/removeOrder", out,new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                System.out.print("Deleted Item");
            }
        } , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.print(error.networkResponse);

            }

        });
        queue.add(lastFMAuthRequest);
    }

    protected void delete(DeleteDecorator dec,Response.Listener<JSONObject> s){
        RequestQueue queue = Volley.newRequestQueue(maind);
        JSONObject out = new JSONObject();
        try{
            out.put("sql",dec.getSql());
        }catch (JSONException e){
            e.printStackTrace();
        }
        JsonObjectRequest lastFMAuthRequest = new JsonObjectRequest(Request.Method.POST, url + "/delete", out,s , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.print(error.networkResponse);

            }

        });
        queue.add(lastFMAuthRequest);
    }


    /**
     * This calls the database for a table of a name given.
     * If two tables exist with the same name it will only get the first one.
     * @param name name of the table you are searching for
     * @param s response function that the response will have the form of {name:String,id:int,x_coord:int,y_coord:int,number_seats:int,status:int}.
     */
    public void getTableByName(String name,Response.Listener<JSONObject> s){
        RequestQueue queue = Volley.newRequestQueue(maind);
        JSONObject out = new JSONObject();
        try{
            out.put("name",name);
        }catch (JSONException e){
            e.printStackTrace();
        }
        JsonObjectRequest lastFMAuthRequest = new JsonObjectRequest(Request.Method.POST, url + "/getTableWithName", out,s , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.print(error.networkResponse);

            }

        });
        queue.add(lastFMAuthRequest);
    }

    protected void getReservation(int index, Response.Listener<JSONObject> s){
        RequestQueue queue = Volley.newRequestQueue(maind);
        JSONObject out = new JSONObject();
        try{
            out.put("index",index);
        }catch (JSONException e){
            e.printStackTrace();
        }
        JsonObjectRequest lastFMAuthRequest = new JsonObjectRequest(Request.Method.POST, url + "/getReservation", out,s , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.print(error.networkResponse);

            }

        });
        queue.add(lastFMAuthRequest);
    }

    protected void getTableByID(int id,Response.Listener<JSONObject> s){
        RequestQueue queue = Volley.newRequestQueue(maind);
        JSONObject out = new JSONObject();
        try{
            out.put("id",id);
        }catch (JSONException e){
            e.printStackTrace();
        }
        JsonObjectRequest lastFMAuthRequest = new JsonObjectRequest(Request.Method.POST, url + "/getTableByID", out,s , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.print(error.networkResponse);

            }

        });
        queue.add(lastFMAuthRequest);
    }




}