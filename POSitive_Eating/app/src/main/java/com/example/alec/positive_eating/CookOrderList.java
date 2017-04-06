package com.example.alec.positive_eating;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;
import com.android.volley.Response;
import org.json.JSONObject;

import static com.example.alec.positive_eating.Singleton_ShaneConnect_Factory.getShaneConnect;
import shaneconnect.ShaneConnect;

public class CookOrderList extends AppCompatActivity {

    private ExpandableListAdapter listAdapter;
    private ExpandableListView expListView;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;
    private ShaneConnect ModelM;
    private int recursiveInc;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_order_list);

        context = getApplicationContext();
        prepareListData();
        Button b = (Button) findViewById(R.id.load);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expListView = (ExpandableListView) findViewById(R.id.lvExp);

                listAdapter = new ExpandableListAdapter(context, listDataHeader, listDataChild);

                expListView.setAdapter(listAdapter);
            }
        });
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        getAllOrders();
    }

    private void getAllOrders() {
        ModelM = getShaneConnect();
        recursiveInc = 0;
        ArrayList<String> itemsInTheOrder = new ArrayList<String>();
        ModelM.getOrders(recursiveInc, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse (JSONObject response){
            if (response.has("none")) {
                    return;
                } else {
                    try {
                        String compString = response.getString("componentString");
                        final String orderNum = "Order #" + response.getInt("order_id");
                        listDataHeader.add(orderNum);
                        ModelM.getFoodByID(compString, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    String tmp = response.toString();
                                    ModelM.getOrders(++recursiveInc, this);
                                } catch(Exception e) {
                                    Toast.makeText(context,
                                        "An error occurred in getFoodByID(). " +
                                                "Please press the back button and try again.",
                                        Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    } catch (Exception e) {
                        Toast.makeText(context,
                            "An error occurred in getOrders(). " +
                                    "Please press the back button and try again.",
                            Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
