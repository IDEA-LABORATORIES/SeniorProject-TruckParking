package com.example.s_sp_truckparking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        bottomNav.getMenu().getItem(1).setChecked(true);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new MapFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            item -> {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.nav_map:
                        selectedFragment = new MapFragment();
                        break;
                    case R.id.nav_alongroute:
                        selectedFragment = new AlongRouteFragment();
                        break;
                    case R.id.nav_saved:
                        selectedFragment = new SavedFragment();
                        break;
                    case R.id.nav_contribute:
                        selectedFragment = new ContributeFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                return true;
            };
}