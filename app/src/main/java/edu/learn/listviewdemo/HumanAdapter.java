package edu.learn.listviewdemo;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
public class HumanAdapter extends ArrayAdapter<Human> {
    Activity activity;
    ArrayList<Human> humanList;
    public HumanAdapter(@NonNull Activity context, int resource, @NonNull ArrayList<Human> objects) {
        super(context, resource, objects);
        this.humanList = objects;
        this.activity = context;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = this.activity.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.item_human_list, null, true);
        ImageView imageView = view.findViewById(R.id.thumbnail);
        TextView nameView = view.findViewById(R.id.name);
        TextView ageView = view.findViewById(R.id.age);
        TextView genderView = view.findViewById(R.id.gender);
        TextView phoneView = view.findViewById(R.id.phone);
        TextView contactView = view.findViewById(R.id.contact_number);
        TextView eamilView = view.findViewById(R.id.email);
        Human human = humanList.get(position);
        Glide.with(activity).load(human.getThumbnail()).into(imageView);
        String name = human.getTitle() + " " + human.getFirstName() + " " + human.getLastName();
        nameView.setText(name);
        ageView.setText(human.getAge() + "");
        genderView.setText(human.getGender());
        phoneView.setText(human.getPhone());
        contactView.setText(human.getCell());
        eamilView.setText(human.getEmail());
        return view;
    }
}
