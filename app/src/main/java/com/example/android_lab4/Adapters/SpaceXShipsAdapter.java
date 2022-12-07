package com.example.android_lab4.Adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android_lab4.Model.SpaceXShip;
import com.example.android_lab4.R;
import com.example.android_lab4.View.SelectedItemFragment;

import java.util.ArrayList;
import java.util.List;

// Adapter for the RecyclerView that will be placed in the View showing the search results
public class SpaceXShipsAdapter extends RecyclerView.Adapter<SpaceXShipsAdapter.SpaceXShipsViewHolder> {

    private final String TAG = "SpaceXShipsAdapter";
    private List<SpaceXShip> ships = new ArrayList<>();
    private FragmentActivity fragmentActivity;

    @NonNull
    @Override
    public SpaceXShipsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ship_layout_for_adapter, parent, false);
        return new SpaceXShipsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpaceXShipsViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder");
        SpaceXShip ship = ships.get(position);
        holder.shipNameView.setText("Ship name: " + ship.getShip_name());
        holder.shipTypeView.setText("Ship type: " + ship.getShip_type());
        holder.shipYearBuilt.setText("Year built: " + Integer.toString(ship.getYear_built()));
        if (ship.getImageURL() != null) {
            Glide.with(holder.itemView).load(ship.getImageURL()).into(holder.shipImageView);
        } else {
            holder.shipImageView.setBackgroundResource(R.drawable.image_not_foudn);
        }
    }

    @Override
    public int getItemCount() {
        return ships.size();
    }

    // must have to prevent images from overlapping
    @Override
    public long getItemId(int position) {
        return position;
    }

    // must have to prevent images from overlapping
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public void setShips(List<SpaceXShip> ships) {
        this.ships = ships;
        Log.d(TAG, "setShips and notifyDataSetChanged");
        notifyDataSetChanged();
    }

    public void setActivity(FragmentActivity fragmentActivity) {
        this.fragmentActivity = fragmentActivity;
    }

    class SpaceXShipsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView shipNameView;
        TextView shipTypeView;
        TextView shipYearBuilt;
        ImageView shipImageView;

        public SpaceXShipsViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            shipImageView = itemView.findViewById(R.id.ship_item_small_thumbnail);
            shipNameView = itemView.findViewById(R.id.ship_name);
            shipTypeView = itemView.findViewById(R.id.ship_type);
            shipYearBuilt = itemView.findViewById(R.id.ship_year_built);
        }

        @Override
        public void onClick(View view) {
            assert (fragmentActivity != null);
            FragmentTransaction ft = fragmentActivity.getSupportFragmentManager().beginTransaction();
            SelectedItemFragment selectedItemFragment = SelectedItemFragment.newInstance(ships.get(getLayoutPosition()).getShip_name());
            ft.replace(R.id.fragment_container, selectedItemFragment).addToBackStack(null).commit();
        }
    }
}
