package com.example.TruckParkingApp.ui.contribute;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.TruckParkingApp.R;

public class ContributeFragment extends Fragment {

    private ContributeViewModel contributeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        contributeViewModel =
                ViewModelProviders.of(this).get(ContributeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_contribute, container, false);
        final TextView textView = root.findViewById(R.id.text_contribute);
        contributeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}