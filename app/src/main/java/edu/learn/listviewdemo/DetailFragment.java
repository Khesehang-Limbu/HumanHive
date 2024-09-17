package edu.learn.listviewdemo;
import android.os.Bundle;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
public class DetailFragment extends Fragment {
    private ImageView detailThumbnail;
    private TextView detailName, detailGender, detailAge, detailEmail, detailPhone, detailCell, detailLocation, detailCoordinates, detailTimezone;
    Human human;
    public DetailFragment() {}
    public static DetailFragment newInstance(Human human) {
        DetailFragment detailFragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putSerializable("human", human);
        detailFragment.setArguments(args);
        return detailFragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.human = (Human) getArguments().getSerializable("human");
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        detailThumbnail = view.findViewById(R.id.detail_thumbnail);
        detailName = view.findViewById(R.id.detail_name);
        detailGender = view.findViewById(R.id.detail_gender);
        detailAge = view.findViewById(R.id.detail_age);
        detailEmail = view.findViewById(R.id.detail_email);
        detailPhone = view.findViewById(R.id.detail_phone);
        detailCell = view.findViewById(R.id.detail_cell);
        detailLocation = view.findViewById(R.id.detail_location);
        detailCoordinates = view.findViewById(R.id.detail_coordinates);
        detailTimezone = view.findViewById(R.id.detail_timezone);
        Glide.with(getContext()).load(human.getLarge()).into((detailThumbnail));
        detailName.setText(human.getTitle() + " " + human.getFirstName() + " " + human.getLastName());
        detailGender.setText(human.getGender());
        detailAge.setText(String.valueOf(human.getAge()));
        detailEmail.setText(human.getEmail());
        detailPhone.setText(human.getPhone());
        detailCell.setText(human.getCell());
        detailLocation.setText(human.getStreetName() + " " + human.getCity() + " " + human.getState() + " " + human.getCountry() + " " + human.getPostcode());
        detailTimezone.setText(human.getOffset() + " " + human.getDescription());
        detailCoordinates.setText(human.getLatitude() + " " + human.getLongitude());
        FrameLayout frameLayout = getActivity().findViewById(R.id.detailFrame);
        frameLayout.setVisibility(View.VISIBLE);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                FrameLayout frameLayout = getActivity().findViewById(R.id.detailFrame);
                frameLayout.setVisibility(View.GONE);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);
    }
}