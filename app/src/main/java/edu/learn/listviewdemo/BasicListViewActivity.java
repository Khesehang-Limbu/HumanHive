package edu.learn.listviewdemo;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
public class BasicListViewActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<String> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.basic_list_activity);
        listView = findViewById(R.id.basicList);
        data = new ArrayList<String>();
        fetchHuman();
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
         String name = data.get(i);
         Toast.makeText(BasicListViewActivity.this, name, Toast.LENGTH_SHORT).show();
        });
    }
    void fetchHuman(){
        String url = "https://randomuser.me/api/?results=10";
        RequestQueue queue = Volley.newRequestQueue(BasicListViewActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("results");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String gender = jsonObject1.get("gender").toString();
                        JSONObject nameObj = jsonObject1.getJSONObject("name");
                        String title = nameObj.get("title").toString();
                        String firstName = nameObj.getString("first");
                        String lastName = nameObj.getString("last");
                        String fullName = title + " " + firstName + " " + lastName;
                        data.add(fullName);
                    }
                    ArrayAdapter<String> arrAdapter = new ArrayAdapter<String>(BasicListViewActivity.this, R.layout.itembasiclist, data);
                    listView.setAdapter(arrAdapter);
                }catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {}
        });
        queue.add(stringRequest);
    }
}