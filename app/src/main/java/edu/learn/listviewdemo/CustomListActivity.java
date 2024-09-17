package edu.learn.listviewdemo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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
public class CustomListActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<Human> humans = new ArrayList<Human>();
    ArrayAdapter<Human> humanArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_custom_list);
        listView = findViewById(R.id.humanList);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Human human = humans.get(i);
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                DetailFragment detailFragment = DetailFragment.newInstance(human);
                fragmentTransaction.replace(R.id.detailFrame, detailFragment);
                fragmentTransaction.setReorderingAllowed(true);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        fetchData();
    }
    void fetchData() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://randomuser.me/api/?results=30";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray results = jsonObject.getJSONArray("results");
                            for (int i = 0; i < results.length(); i++) {
                                JSONObject humanObj = results.getJSONObject(i);
                                String gender = humanObj.getString("gender");
                                JSONObject nameObj = humanObj.getJSONObject("name");
                                String title = nameObj.getString("title");
                                String first = nameObj.getString("first");
                                String last = nameObj.getString("last");
                                JSONObject locationObj = humanObj.getJSONObject("location");
                                String city = locationObj.getString("city");
                                String country = locationObj.getString("country");
                                String state = locationObj.getString("state");
                                String postcode = locationObj.getString("postcode");
                                JSONObject streetObj = locationObj.getJSONObject("street");
                                String number = streetObj.getString("number");
                                String streetName = streetObj.getString("name");
                                JSONObject coordinate = locationObj.getJSONObject("coordinates");
                                String latitude = coordinate.getString("latitude");
                                String longitude = coordinate.getString("longitude");
                                JSONObject timeZone = locationObj.getJSONObject("timezone");
                                String offset = timeZone.getString("offset");
                                String description = timeZone.getString("description");
                                JSONObject dob = humanObj.getJSONObject("dob");
                                int age = dob.getInt("age");
                                String date = dob.getString("date");
                                String email = humanObj.getString("email");
                                String phone = humanObj.getString("phone");
                                String cell = humanObj.getString("cell");
                                JSONObject loginObj = humanObj.getJSONObject("login");
                                String username = loginObj.getString("username");
                                String password = loginObj.getString("password");
                                JSONObject pictureObj = humanObj.getJSONObject("picture");
                                String large = pictureObj.getString("large");
                                String medium = pictureObj.getString("medium");
                                String thumbnail = pictureObj.getString("thumbnail");
                                String nat = humanObj.getString("nat");
                                Human human = new Human();
                                human.setTitle(title);
                                human.setGender(gender);
                                human.setFirstName(first);
                                human.setLastName(last);
                                human.setCity(city);
                                human.setState(state);
                                human.setCountry(country);
                                human.setCell(cell);
                                human.setAge(age);
                                human.setDate(date);
                                human.setPostcode(postcode);
                                human.setNumber(number);
                                human.setStreetName(streetName);
                                human.setLatitude(latitude);
                                human.setLongitude(longitude);
                                human.setOffset(offset);
                                human.setDescription(description);
                                human.setUsername(username);
                                human.setPassword(password);
                                human.setEmail(email);
                                human.setPhone(phone);
                                human.setMedium(medium);
                                human.setLarge(large);
                                human.setThumbnail(thumbnail);
                                human.setNat(nat);
                                humans.add(human);
                            }
                            HumanAdapter humanAdapter = new HumanAdapter(CustomListActivity.this, R.layout.item_human_list, humans);
                            listView.setAdapter(humanAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
        requestQueue.add(stringRequest);
    }
}


