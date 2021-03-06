package com.example.alec.positive_eating.customerRegisteration;

/**
 * Created by shane on 4/13/17.
 */

public class Customer {

    private String userName;

    private String email;

    private String password;

    private int id;

    public Customer(String username, String email, String password){
        this.userName = username;
        this.email = email;
        this.password = password;
        id = 2;
    }

    public Customer(){

    }

    public void setUserName(String user){
        this.userName = user;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getUserName(){
        return this.userName;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }

    public int getID() {
        return this.id;
    }

}
