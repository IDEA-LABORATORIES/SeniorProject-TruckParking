package com.example.s_sp_truckparking;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SavedFragment extends Fragment {

    ListView lvSpots;
    String[] spots;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saved, container, false);

        lvSpots = (ListView) view.findViewById(R.id.lvSpots);
        spots = new String[]{"Parking Spot 1", "Parking Spot 2", "Parking Spot 3", "Parking Spot 4", "Parking Spot 5"};

        ArrayAdapter<String> spotsAdapter = new ArrayAdapter<>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, spots);
        lvSpots.setAdapter(spotsAdapter);

        return view;
    }
}
