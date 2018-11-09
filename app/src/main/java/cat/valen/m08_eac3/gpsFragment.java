package cat.valen.m08_eac3;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;


/**
 *
 */
public class gpsFragment extends Fragment implements LocationListener, OnMapReadyCallback {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    LocationManager gestorLoc;

    private MapView mMapView;

    private GoogleMap mGoogleMap;

    public gpsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment gpsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static gpsFragment newInstance(String param1, String param2) {
        gpsFragment fragment = new gpsFragment();
        Bundle args = new Bundle();
        args.putString( ARG_PARAM1, param1 );
        args.putString( ARG_PARAM2, param2 );
        fragment.setArguments( args );
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        if (getArguments() != null) {
            mParam1 = getArguments().getString( ARG_PARAM1 );
            mParam2 = getArguments().getString( ARG_PARAM2 );
        }


       /* getActivity().setContentView( R.layout.activity_maps );
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getActivity().getSupportFragmentManager()
                .findFragmentById( R.id.map );
        mapFragment.getMapAsync( this );*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate( R.layout.fragment_gps, container, false );

        // Gets the MapView from the XML layout and creates it
        mMapView = (MapView) v.findViewById( R.id.mapView );
        mMapView.onCreate( savedInstanceState );

        // Gets to GoogleMap from the MapView and does initialization stuff
        mMapView.getMapAsync( this );
        //mGoogleMap.getUiSettings().setMyLocationButtonEnabled( false );
        if (ActivityCompat.checkSelfPermission( getContext(), Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission( getContext(), Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            //return TODO;
        }

        // Inflate the layout for this fragment
        gestorLoc = (LocationManager) getActivity().getSystemService( Context.LOCATION_SERVICE );
        gestorLoc.requestLocationUpdates( LocationManager.GPS_PROVIDER, 1000, 1, this );

        //Location location = gestorLoc.getLastKnownLocation( LocationManager.GPS_PROVIDER);
        /*String text = "Posició actual:\n" +
                "Latitud = " + getLastKnownLocation().getLatitude() + "\n" +
                "Longitud = " + getLastKnownLocation().getLongitude();

        Toast.makeText(getContext().getApplicationContext(), text,
                Toast.LENGTH_LONG).show();*/

        return inflater.inflate( R.layout.fragment_gps, container, false );
    }


    @Override
    public void onLocationChanged(Location location) {
        String text = "Posició actual:\n" +
                "Latitud = " + location.getLatitude() + "\n" +
                "Longitud = " + location.getLongitude();

        Toast.makeText( getContext().getApplicationContext(), text,
                Toast.LENGTH_LONG ).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    private Location getLastKnownLocation() {
        //mLocationManager = (LocationManager)getApplicationContext().getSystemService(LOCATION_SERVICE);
        List<String> providers = gestorLoc.getProviders( true );
        Location bestLocation = null;
        for (String provider : providers) {
            @SuppressLint("MissingPermission") Location l = gestorLoc.getLastKnownLocation( provider );
            if (l == null) {
                continue;
            }
            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                // Found best last known location: %s", l);
                bestLocation = l;
            }
        }
        return bestLocation;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mGoogleMap = googleMap;
        mGoogleMap.getUiSettings().setZoomControlsEnabled( true );
        /*mGoogleMap.addMarker(new MarkerOptions().position(*//*some location*//*));
        mGoogleMap.moveCamera( CameraUpdateFactory.newLatLngZoom(*//*some location*//*, 10));*/
        if (ActivityCompat.checkSelfPermission( getContext(), Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission( getContext(), Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            mGoogleMap.setMyLocationEnabled( true );
            return;
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}
