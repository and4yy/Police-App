package company.override.huzykamz.policecopuganda;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

public class SinglePoliceDetailsActivity  extends AppCompatActivity implements OnMapReadyCallback{


    private TextView policename, oc_name, contact_one, contact_two, police_details;
    String PoliceName, Oc_Name,Contact_One,Contact_Two,Police_Details, Latitude,Longitude ,Oc_Pic;
    private ImageView oc_pic;
    private GoogleMap mMap;
    private String Image_Url="http://192.168.43.104:8092/COOPERP/PoliceDetailsPhotos/";
  //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_police_details);
     //   getSupportActionBar().setDisplayShowHomeEnabled(true);
     //   android.support.v7.app.ActionBar actionBar = getSupportActionBar();
       // actionBar.setDisplayHomeAsUpEnabled(true);
      //  getSupportActionBar().setTitle("Police Station Details");




        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


  //     policename =(TextView)findViewById(R.id.poice_name_txt);
        oc_name =(TextView) findViewById(R.id.oc_name_txt);
        contact_one =(TextView) findViewById(R.id.contact_one_txt);
        contact_two =(TextView) findViewById(R.id.contact_two_txt);
        police_details=(TextView) findViewById(R.id.details_police_txt);
        oc_pic =(ImageView) findViewById(R.id.image_oc);
        Intent inn = getIntent();
        if(inn!= null) {
            PoliceName = inn.getStringExtra("Police_Name");
            Oc_Name =inn.getStringExtra("Oc_Name");
            Contact_One =inn.getStringExtra("Contact_One");
            Contact_Two =inn.getStringExtra("Contact_Two");
            Police_Details =inn.getStringExtra("Police_Details");
            Oc_Pic = inn.getStringExtra("Oc_Pic");

            Latitude =inn.getStringExtra("Latitude");
            Longitude =inn.getStringExtra("Longitude");

            getSupportActionBar().setDisplayShowHomeEnabled(true);
            android.support.v7.app.ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(PoliceName);

         //   policename.setText(PoliceName);
            oc_name.setText(Oc_Name);
            contact_one.setText(Contact_One);
            contact_two.setText(Contact_Two);
            police_details.setText(Police_Details);
            Picasso.with(SinglePoliceDetailsActivity.this).load(Image_Url+Oc_Pic).resize(60,70).error(R.mipmap.ic_launcher).
                    placeholder(R.mipmap.ic_launcher).into(oc_pic);
            System.out.println("Image url :" + Image_Url+Oc_Pic);

        }



    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
      //  ActionBarActivity activity = ActionBarActivity();
        //activity.setSupportActionBar(toolbar);
      //  activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return super.onCreateView(name, context, attrs);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Intent inn = getIntent();
        if(inn!= null) {
            PoliceName = inn.getStringExtra("Police_Name");
            Oc_Name =inn.getStringExtra("Oc_Name");
            Contact_One =inn.getStringExtra("Contact_One");
            Contact_Two =inn.getStringExtra("Contact_Two");
            Police_Details =inn.getStringExtra("Police_Details");
            Latitude =inn.getStringExtra("Latitude");
            Longitude =inn.getStringExtra("Longitude");



        }
        double lat = Double.parseDouble(Latitude);
        double log = Double.parseDouble(Longitude);
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(lat, log);
        mMap.addMarker(new MarkerOptions().position(sydney).title(PoliceName));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.setMinZoomPreference(7.0f);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                break;


        }
        return super.onOptionsItemSelected(item);
    }
}
