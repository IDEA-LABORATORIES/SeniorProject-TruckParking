package com.example.s_sp_truckparking;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.GeoPoint;

import java.util.Objects;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    FirebaseFirestore db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        db = FirebaseFirestore.getInstance();

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Check for location permissions
        if (ActivityCompat.checkSelfPermission(Objects.requireNonNull(getActivity()).getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }
        googleMap.setMyLocationEnabled(true);


        displayProgrammedMarkersOnMap(googleMap);
        // displayFirebaseMarkersOnMap(googleMap);
    }

    private void displayProgrammedMarkersOnMap(GoogleMap googleMap) {
        LatLng cord1 = new LatLng(37.21930951590881, -77.40484504845894);
        googleMap.addMarker(new MarkerOptions()
                .position(cord1));

        LatLng cord2 = new LatLng(37.219856307665324, -77.40772037622251);
        googleMap.addMarker(new MarkerOptions()
                .position(cord2));

        LatLng cord3 = new LatLng(37.21631063439086, -77.40322499438318);
        googleMap.addMarker(new MarkerOptions()
                .position(cord3));

        LatLng cord4 = new LatLng(37.220514161244274, -77.40343957108196);
        googleMap.addMarker(new MarkerOptions()
                .position(cord4));

        LatLng cord5 = new LatLng(37.21646442608674, -77.40221648389894);
        googleMap.addMarker(new MarkerOptions()
                .position(cord5));
    }

    private void displayFirebaseMarkersOnMap(GoogleMap googleMap) {
        // creating a variable for document reference.
        DocumentReference documentReference = db.collection("MapsData").document("7QWDor9vozLaHdFYV9kh");

        // calling document reference class with on snap shot listener.
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value != null && value.exists()) {
                    // below line is to create a geo point and we are getting
                    // geo point from firebase and setting to it.
                    GeoPoint geoPoint = value.getGeoPoint("geoPoint");

                    // getting latitude and longitude from geo point
                    // and setting it to our location.
                    assert geoPoint != null;
                    LatLng location = new LatLng(geoPoint.getLatitude(), geoPoint.getLongitude());

                    // adding marker to each location on google maps
                    googleMap.addMarker(new MarkerOptions().position(location).title("Marker"));

                    // below line is use to move camera.
                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(location));
                } else {
                    Toast.makeText(getActivity(), "Error found is " + error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
