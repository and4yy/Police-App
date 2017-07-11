package company.override.huzykamz.policecopuganda;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import adapter.MainActivityAdapter;
import adapter.MenuAdapter;
import adapter.MenuAdapterTwo;
import adapter.NewsAdapter;
import adapter.PoliceDetailsAdapter;
import model.Details;
import model.Districts;
import model.ItemObjects;
import model.LocalLeaderModel;
import model.MenuItems;
import model.NationalID_Model;
import model.News;
import model.Profile;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback

        {


    private RecyclerView rv,rv_menu_main,rv_menu_main_two;
    SliderLayout sliderShow;
    private static String PHOTO_BASE_URL ="http://192.168.43.104:8092/COOPERP/NewsImages/";



    private Marker mPoliceMarker;
    private GoogleMap mMap;
    private Marker mPerth;
    private Marker mSydney;
    private Marker mBrisbane;
    private List<News> actualitem;
    private int postion;


    //  private GridLayoutManager lLayout;
  private SQLiteDatabase db;
    private TextView district_settings_txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Uganda Police App");



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

//

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.Activitymap);
        mapFragment.getMapAsync(this);
        //
        sliderShow = (SliderLayout) findViewById(R.id.slider);

        //Setting District Setting
        GetDistricts();




        DataProvider db = new DataProvider(getApplicationContext());


        String  District= db.GetDistrictName();
        district_settings_txt =(TextView)findViewById(R.id.district_settings_txt);
        district_settings_txt.setText(District);








        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);




        navigationView.setNavigationItemSelectedListener(this);
        List<MenuItems> rowListItem = getAllItemList();
        List<MenuItems> rowListItemTwo = getAllItemsTwo();



        rv_menu_main =(RecyclerView)findViewById(R.id.rv_menu_main);
        rv_menu_main_two =(RecyclerView)findViewById(R.id.rv_menu_main_two);



        GridLayoutManager lm = new GridLayoutManager(this,1,GridLayoutManager.HORIZONTAL,true);
      //  rv_menu_main.setLayoutManager(new GridLayoutManager(this,1,GridLayoutManager.HORIZONTAL, true));
          lm.setReverseLayout(true);


        GridLayoutManager lm2 = new GridLayoutManager(this,1,GridLayoutManager.HORIZONTAL,true);
        //  rv_menu_main.setLayoutManager(new GridLayoutManager(this,1,GridLayoutManager.HORIZONTAL, true));
        lm2.setReverseLayout(true);

      //   lm.setStackFromEnd(true);
        rv_menu_main.setHasFixedSize(true);
        rv_menu_main.setLayoutManager(lm);

        rv_menu_main_two.setHasFixedSize(true);
        rv_menu_main_two.setLayoutManager(lm2);

        MenuAdapterTwo rcAdaptertwo = new MenuAdapterTwo(MainActivity.this, rowListItemTwo);
        rv_menu_main_two.setAdapter(rcAdaptertwo);


        MenuAdapter rcAdapter = new MenuAdapter(MainActivity.this, rowListItem);
        rv_menu_main.setAdapter(rcAdapter);


      /*  //Making a RecyclerView to reverse , by Lutaaya Huzaifah Idris

      LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        mRecyclerview_event_view.setLayoutManager(layoutManager);
        createDatabase();*/

        GetNews();

    }




   public void OnSelectDistrict(View v){

       Intent i = new Intent(MainActivity.this,SelectDistrict.class);
       startActivity(i);
   }

    void GetDistricts(){




        final  ProgressBar progressBar_D= (ProgressBar) findViewById(R.id.progressBar_D);

        final String url = "http://192.168.43.104:8092/PoliceApp/Default.aspx?DataFormat=Districts";


        progressBar_D.setVisibility(View.VISIBLE);



        Ion.with(getBaseContext())
                .load(url)
                .progressBar(progressBar_D)
                .as(new TypeToken<List<Districts>>() {
                })
                .setCallback(new FutureCallback<List<Districts>>() {

                    @Override
                    public void onCompleted(Exception e, final List<Districts> itemList) {


                        try {

                            if(itemList!= null) {

                                final List<String> items = new ArrayList<String>();



                                for ( int i = 0; i < itemList.size(); i++) {
                                    items.add(itemList.get(i).getDistrictName());


                                    DataProvider DB = new DataProvider(getBaseContext());
                                    String DistrictsName = itemList.get(i).getDistrictName();
                                    String Id = itemList.get(i).getDistrictsId();
                                    int DistrictId = Integer.parseInt(Id);
                                    DB.InsertDistricts(DistrictsName,DistrictId);


                                }
                                progressBar_D.setVisibility(View.GONE);



                            }
                            else {
                                Toast.makeText(getApplicationContext(), "Check Your Internet Connection Please!", Toast.LENGTH_SHORT).show();
                                progressBar_D.setVisibility(View.GONE);
                            }

                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "No Details found!", Toast.LENGTH_SHORT).show();
                            progressBar_D.setVisibility(View.GONE);
                        }
                    }
                });





    }




    public void OnPoliceDirectory(View v){

       Intent i = new Intent(MainActivity.this,PoliceDetails.class);
        startActivity(i);

    }
    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        //  ActionBarActivity activity = ActionBarActivity();
        //activity.setSupportActionBar(toolbar);
        //  activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return super.onCreateView(name, context, attrs);
    }





    private List<MenuItems> getAllItemsTwo(){
        List<MenuItems> allItems = new ArrayList<MenuItems>();

        allItems.add(new MenuItems("Report Accident", R.drawable.accident_icon));
        allItems.add(new MenuItems("Local Leaders", R.drawable.leaders_icon));
        allItems.add(new MenuItems("Traffic Offence", R.drawable.traffic_offence));
        allItems.add(new MenuItems("Permit & ID", R.drawable.passport));
        allItems.add(new MenuItems("Passport", R.drawable.passport));
        allItems.add(new MenuItems("Traffic Signs", R.drawable.traffic_offence));
        allItems.add(new MenuItems("Diaspora", R.drawable.leaders_icon));
        allItems.add(new MenuItems("Car Verify", R.drawable.car_verify));
        return allItems;
    }
    private List<MenuItems> getAllItemList(){

        List<MenuItems> allItems = new ArrayList<MenuItems>();
        allItems.add(new MenuItems("Missing Citizen", R.drawable.missingcitizen_menu));
        allItems.add(new MenuItems("Shake For Help", R.drawable.shake_menu_icon));
        allItems.add (new MenuItems("Police Directory", R.drawable.directory_menu_icon));
        allItems.add(new MenuItems("Crime Reporting", R.drawable.crime_menu_icon));
        allItems.add(new MenuItems("Wanted List", R.drawable.wanted));
        allItems.add(new MenuItems("About", R.drawable.about_menu_icon));

        return allItems;
    }

    public void OnCrimeReport(View v){
        Intent  i = new Intent(MainActivity.this,CrimesReporting.class);
        startActivity(i);
    }

    public void OnMissingCitizen(View v){
       Toast.makeText(getApplicationContext(),"under construction",Toast.LENGTH_LONG).show();
    }

    public void OnHelpMe(View v){
        Toast.makeText(getApplicationContext(),"under construction",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

// This method is called when map is ready ...
    @Override
    public void onMapReady(final GoogleMap googleMap) {





            DataProvider db = new DataProvider(getApplicationContext());




            final String url = "http://192.168.43.104:8092/PoliceApp/Default.aspx?DataFormat=PoliceDetails&District="+db.GetDistrictName();
            final ProgressBar pd;
            pd = (ProgressBar) findViewById(R.id.pb_map_activity);
            pd.setVisibility(View.VISIBLE);



            Ion.with(MainActivity.this )
                    .load(url)
                    .progressBar(pd)
                    .as(new TypeToken<List<Details>>() {
                    })
                    .setCallback(new FutureCallback<List<Details>>() {

                        @Override
                        public void onCompleted(Exception e, List<Details> itemList) {
                         //   final PoliceDetailsAdapter adapter = new PoliceDetailsAdapter(itemList, getApplicationContext());

                            try {




                                for (int i =0; i<itemList.size(); i++) {

                                    Toast.makeText(MainActivity.this,"Contact  :"+itemList.get(i).getContactOne()+
                                                    "Name OC :" + itemList.get(i).getOc_Name(),
                                            Toast.LENGTH_LONG).show();
                                }


                              /*  for (int i =0; i<itemList.size(); i++) {
                                    double lat = Double.parseDouble(itemList.get(i).getLatitude());


                                    double log = Double.parseDouble(itemList.get(i).getLongitude());
                                    Toast.makeText(MainActivity.this,"Lat :"+lat+
                                                    "Long :" +log,
                                            Toast.LENGTH_LONG).show();
                                    LatLng latlong = new LatLng(lat,log);
                                    mMap = googleMap;
                                    mPoliceMarker = mMap.addMarker(new MarkerOptions().position(latlong).title("Hol").draggable(true)
                                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
                                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latlong));
                                    mMap.setMinZoomPreference(10.0f);
                                    mPoliceMarker.setTag(0);
                                }*/
                               /* for (Details details : itemList) {
                                        double lat = Double.parseDouble(details.getLatitude());
                                        double log = Double.parseDouble(details.getLongitude());


                                        Toast.makeText(MainActivity.this,"Lat :"+details.getLatitude()+
                                                "Long :" +details.getLongitude(),
                                                Toast.LENGTH_LONG).show();

                                        //

                                        LatLng latlong = new LatLng(lat,log);
                                        mMap = googleMap;
                                        mPoliceMarker = mMap.addMarker(new MarkerOptions().position(latlong).title(details.getStationName()).draggable(true)
                                               .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
                                       mMap.moveCamera(CameraUpdateFactory.newLatLng(latlong));
                                        mMap.setMinZoomPreference(10.0f);
                                      mPoliceMarker.setTag(0);



                                    pd.setVisibility(View.GONE);
                                }
                        */
                                }

                            catch (Exception ex){
                                Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
                                pd.setVisibility(View.GONE);
                            }
                        }
                    });










    }




    @Override
    protected void onStop() {
        sliderShow.stopAutoCycle();
        super.onStop();
    }


/*

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
*/





    void GetNews(){

        // 192.168.43.104
        final String url = "http://192.168.43.104:8092/PoliceApp/Default.aspx?DataFormat=News";

        final ProgressBar pd;
        pd = (ProgressBar) findViewById(R.id.progressBar_News);
        pd.setVisibility(View.VISIBLE);
        Ion.with(MainActivity.this)
                .load(url)
                .progressBar(pd)
                .as(new TypeToken<List<News>>() {
                })
                .setCallback(new FutureCallback<List<News>>() {

                    @Override
                    public void onCompleted(Exception e, final List<News> itemList) {
                        try {



                        if (itemList!=null) {

                            HashMap<String, String> url_maps = new HashMap<String, String>();


                            for (News headlines : itemList) {



                                DataProvider db = new DataProvider(getApplicationContext());
                                News n = new News();
                                String comm = db.GetNewsData(n,headlines.getHeadlines());
                                url_maps.put(n.getHeadlines(), PHOTO_BASE_URL + n.getNewsPhoto());


                                actualitem = itemList;
                                postion = itemList.size();

                                db.UpdateNews();

                                db.InsertNews(headlines.getHeadlines(),headlines.getDetails()
                                ,headlines.getNewsPhoto(),headlines.getNewsdate());

                            }

                            for (String name : url_maps.keySet()) {
                                TextSliderView textSliderView = new TextSliderView(getApplicationContext());
                                // initialize a SliderLayout

                               // Toast.makeText(MainActivity.this,""+name,Toast.LENGTH_LONG).show();
                                textSliderView
                                        .description(name)
                                        .image(url_maps.get(name));


                                //add your extra information
                                textSliderView.bundle(new Bundle());
                                textSliderView.getBundle()
                                        .putString("extra", name);

                                textSliderView
                                        .description(name)
                                        .image(url_maps.get(name))
                                        .setScaleType(BaseSliderView.ScaleType.Fit)
                                        .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                                            @Override
                                            public void onSliderClick(BaseSliderView slider) {


                                                //Toast.makeText(getApplicationContext(),"Hello ",Toast.LENGTH_LONG).show();


                                                DataProvider DB = new DataProvider(getBaseContext());
                                                News n = new News();
                                                String comm = DB.GetNewsData(n, slider.getBundle().get("extra").toString());
                                                Intent i = new Intent(MainActivity.this, SingleNewsActivity.class);
                                                i.putExtra("headlines",n.getHeadlines());
                                                i.putExtra("details",n.getDetails());
                                                i.putExtra("image",n.getNewsPhoto());
                                                i.putExtra("date",n.getNewsdate());


                                                startActivity(i);
                                            }
                                        });

                                sliderShow.addSlider(textSliderView);

                            }

                              pd.setVisibility(View.GONE);
                        }

                        else {

                           // Toast.makeText(getApplication(),"Item Numbers "+itemList.size(),Toast.LENGTH_LONG).show();
                            final HashMap<String, Integer> url_maps = new HashMap<String, Integer>();


                                url_maps.put("Police App keeps law and order in the society", R.drawable.app);
                                url_maps.put("Tell about others this App, and you keep yourself safe", R.drawable.theft);





                            for (String name : url_maps.keySet()) {
                                TextSliderView textSliderView = new TextSliderView(getApplicationContext());
                                // initialize a SliderLayout
                                textSliderView
                                        .description(name)
                                        .image(url_maps.get(name));


                                //add your extra information
                                textSliderView.bundle(new Bundle());
                                textSliderView.getBundle()
                                        .putString("extra", name);

                                textSliderView
                                        .description(name)
                                        .image(url_maps.get(name))
                                        .setScaleType(BaseSliderView.ScaleType.Fit)
                                        .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                                            @Override
                                            public void onSliderClick(BaseSliderView slider) {
                                                Toast.makeText(getApplicationContext(),"Check your internet Connectivity please!!! ",Toast.LENGTH_LONG).show();


                                            }
                                        });


                                sliderShow.addSlider(textSliderView);
                                pd.setVisibility(View.GONE);
                            }

                        }


                          }
                        catch (Exception ex){
                     //       Toast.makeText(getApplicationContext(), "No News found!" + ex.getMessage(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(), "No News found!" , Toast.LENGTH_SHORT).show();

                            //System.out.println("Ex mesage" + ex.getMessage()+ " E message "+e.getMessage());


                            pd.setVisibility(View.GONE);
                        }
                    }
                });
    }







/*
    @Override
    public void onSliderClick(BaseSliderView slider)
    {
        final  News n = actualitem.get(postion);
        Intent i = new Intent(MainActivity.this, SingleNewsActivity.class);
        i.putExtra("Headlines",n.getHeadlines());
        i.putExtra("Details",n.getDetails());
        i.putExtra("Image",n.getNewsPhoto());
        i.putExtra("Date",n.getDate());

        startActivity(i);

    }*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);//Menu Resource, Menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.select_district) {


            Intent i = new Intent(MainActivity.this,SelectDistrict.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent i ;

        if (id == R.id.police_directory) {
             i = new Intent(MainActivity.this,PoliceDetails.class);
            startActivity(i);


            // Handle the camera action
        } else if (id == R.id.crimes) {
            i = new Intent(MainActivity.this,CrimesReporting.class);
            startActivity(i);

        }

        else if (id == R.id.police_admin) {
            i = new Intent(MainActivity.this,PoliceLogin.class);
            startActivity(i);

        }
        else if (id == R.id.shake) {

            i = new Intent(MainActivity.this,ShakeActivity.class);
            startActivity(i);

        } else if (id == R.id.missing_citizen_menu) {
             i = new Intent(MainActivity.this, MissingTabs.class);
            startActivity(i);

        } else if (id == R.id.report_accident) {

            i = new Intent(MainActivity.this, ReportAccident.class);
            startActivity(i);

        } else if (id == R.id.traffic_offence) {
            i = new Intent(MainActivity.this, TrafficOffences.class);
            startActivity(i);
        }
        else if (id == R.id.about_menu) {
            i = new Intent(MainActivity.this, About.class);
            startActivity(i);
        }
        else if (id == R.id.traffisigns_menu) {
            i = new Intent(MainActivity.this, TrafficSigns.class);
            startActivity(i);
        }
        else if (id == R.id.permit_menu) {
            i = new Intent(MainActivity.this, ID_Verification.class);
            startActivity(i);
        }
        else if (id == R.id.passport_menu) {
            i = new Intent(MainActivity.this, PassportActivity.class);
            startActivity(i);
        }
        else if (id == R.id.localleaders_menu) {
            i = new Intent(MainActivity.this, LocalLeadersMenu.class);
            startActivity(i);
        }
        else if (id == R.id.diaspora_menu) {
            i = new Intent(MainActivity.this, Diaspora.class);
            startActivity(i);
        }
        else if (id == R.id.wantedlist_menu) {
            i = new Intent(MainActivity.this, WantedList.class);
            startActivity(i);
        }
        else if (id == R.id.car_verification_menu) {
            i = new Intent(MainActivity.this, VehicleTabs.class);
            startActivity(i);
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
