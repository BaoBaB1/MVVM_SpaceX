package com.example.android_lab4.View;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;
import com.example.android_lab4.API.SpaceXShipDAO;
import com.example.android_lab4.Model.Mission;
import com.example.android_lab4.Model.Position;
import com.example.android_lab4.Model.SpaceXShip;
import com.example.android_lab4.Model.SpaceXShipDatabase;
import com.example.android_lab4.R;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class SelectedItemFragment extends Fragment {

    private final String TAG = "SelectedItemFragment";
    private String ship_name;
    private ImageView shipImage;
    private TextView shipInfo;
    SpaceXShip ship;

    public static SelectedItemFragment newInstance(String ship_name) {
        SelectedItemFragment selectedItemFragment = new SelectedItemFragment();
        Bundle args = new Bundle();
        args.putString("ship_name", ship_name);
        selectedItemFragment.setArguments(args);
        return selectedItemFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.ship_name = getArguments().getString("ship_name");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.selected_item_layout, container, false);
    }

    private void fillShipInfo(final View view) {
        SpaceXShipDatabase database = SpaceXShipDatabase.getInstance(getActivity().getApplicationContext());
        assert (database != null);
        SpaceXShipDAO spaceXShipDAO = database.spaceXShipDAO();
        assert (spaceXShipDAO != null);

        final CountDownLatch latch = new CountDownLatch(1);
        try {
            // select ship from DB ship
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    Log.d(TAG, "getShipByName");
                    ship = spaceXShipDAO.getShipByName(ship_name);
                    Log.d(TAG, "Ship found");
                    latch.countDown();
                }
            });
            // wait until ship is found
            latch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        shipInfo.append("ship_id: " + ship.getShip_id() + "\n");
        shipInfo.append("ship_name: " + ship.getShip_name() + "\n");
        shipInfo.append("ship_model: " + ship.getShip_model() + "\n");
        shipInfo.append("ship_type: " + ship.getShip_type() + "\n");
        List<String> roles = ship.getRoles();
        if (!roles.isEmpty()) {
            shipInfo.append("roles: ");
            for (String role : roles) {
                shipInfo.append(role + "; ");
            }
            shipInfo.append("\n");
        } else {
            shipInfo.append("roles: null\n");
        }
        shipInfo.append("active: " + ship.isActive() + "\n");
        shipInfo.append("imo: " + ship.getImo() + "\n");
        shipInfo.append("mmsi: " + ship.getMmsi() + "\n");
        shipInfo.append("abs: " + ship.getAbs() + "\n");
        shipInfo.append("class: " + ship.get_class() + "\n");
        shipInfo.append("weight_lbs: " + ship.getWeight_lbs() + "\n");
        shipInfo.append("weight_kg: " + ship.getWeight_kg() + "\n");
        shipInfo.append("year_built: " + ship.getYear_built() + "\n");
        shipInfo.append("home_port: " + ship.getHome_port() + "\n");
        shipInfo.append("status: " + ship.getStatus() + "\n");
        shipInfo.append("speed_kn: " + ship.getSpeed_kn() + "\n");
        shipInfo.append("course_deg: " + ship.getCourse_deg() + "\n");
        if (ship.getPosition() != null) {
            Position pos = ship.getPosition();
            shipInfo.append("latitude: " + pos.getLatitude() + "\n");
            shipInfo.append("longitude: " + pos.getLongitude() + "\n");
        }
        shipInfo.append("successful_landings: " + ship.getSuccessful_landings() + "\n");
        shipInfo.append("attempted_landings: " + ship.getAttempted_landings() + "\n");
        List<Mission> missions = ship.getMissions();
        if (missions != null) {
            shipInfo.append("missions:\n");
            int c = 1;
            for (Mission m : missions) {
                shipInfo.append(c + ") " + m.getName() + " ; flight " + m.getFlight() + "\n");
                ++c;
            }
        } else {
            shipInfo.append("missions: null");
        }
        if (ship.getImageURL() != null) {
            Glide.with(view).load(ship.getImageURL()).into(shipImage);
        } else {
            shipImage.setBackgroundResource(R.drawable.image_not_foudn);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        shipImage = (ImageView)view.findViewById(R.id.ship_image);
        shipInfo = (TextView) view.findViewById(R.id.shipInfoView);
        ImageButton returnButton = (ImageButton) view.findViewById(R.id.return_button);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                assert (fm.getBackStackEntryCount() > 0);
                // go to prev fragment
                getFragmentManager().popBackStack();
            }
        });
        fillShipInfo(view);
    }
}
