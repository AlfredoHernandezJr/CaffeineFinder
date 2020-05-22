package edu.miracostacollege.cs134.caffeinefinder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import edu.miracostacollege.cs134.caffeinefinder.model.Location;

public class DetailsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap map ;

    private static LatLng locationOnMap ;
    private static String locationName ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView nameTextView = findViewById(R.id.nameTextView) ;
        TextView addressTextView = findViewById(R.id.addressTextView) ;
        TextView cityTextView = findViewById(R.id.cityTextView) ;
        TextView stateTextView = findViewById(R.id.stateTextView) ;

        TextView zipCodeTextView = findViewById(R.id.zipCodeTextView) ;
        TextView phoneTextView = findViewById(R.id.phoneTextView) ;
        TextView latitudeTextView = findViewById(R.id.latitudeTextView) ;
        TextView longitudeTextView = findViewById(R.id.longitudeTextView) ;

        Location location = getIntent().getExtras().getParcelable("SelectedLocation") ;

        locationOnMap = new LatLng(location.getLatitude(), location.getLongitude()) ;
        locationName = location.getName() ;

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.mapsFragment) ;
        mapFragment.getMapAsync(this) ;

        nameTextView.setText(location.getName()) ;
        addressTextView.setText(location.getAddress()) ;
        cityTextView.setText(location.getCity()) ;
        stateTextView.setText(location.getState()) ;

        zipCodeTextView.setText(location.getZipCode()) ;
        phoneTextView.setText(location.getPhone()) ;
        latitudeTextView.setText(String.valueOf(location.getLatitude())) ;
        longitudeTextView.setText(String.valueOf(location.getLongitude())) ;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap ;

        map.addMarker(new MarkerOptions().title(locationName).position(locationOnMap)) ;

        CameraPosition position  = new CameraPosition.Builder().target(locationOnMap).zoom(15f).build() ;
        CameraUpdate update = CameraUpdateFactory.newCameraPosition(position) ;
        map.moveCamera(update) ;
    }
}
