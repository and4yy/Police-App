package company.override.huzykamz.policecopuganda;

import android.app.ProgressDialog;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.List;
import java.util.zip.Inflater;

import adapter.NewsAdapter;
import adapter.PoliceDetailsAdapter;
import model.Details;
import model.News;

public class PoliceDetails extends AppCompatActivity {
    public RecyclerView rv_details_police;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police_details);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Police Directory");
        rv_details_police = (RecyclerView)findViewById(R.id.rv_details_police);
        rv_details_police.hasFixedSize();
        rv_details_police.setLayoutManager(new LinearLayoutManager(this));


               GetPoliceDetails();
    }




    void GetPoliceDetails(){

        DataProvider db = new DataProvider(getApplicationContext());
        String dist =  db.GetDistrictName();

        final String url = "http://192.168.43.104:8092/PoliceApp/Default.aspx?DataFormat=PoliceDetails&District="+dist;


        final ProgressBar pd;
        pd = (ProgressBar) findViewById(R.id.pb_details_police);
        pd.setVisibility(View.VISIBLE);



        Ion.with(getBaseContext())
                .load(url)
                .progressBar(pd)
                .as(new TypeToken<List<Details>>() {
                })
                .setCallback(new FutureCallback<List<Details>>() {

                    @Override
                    public void onCompleted(Exception e, List<Details> itemList) {
                        final PoliceDetailsAdapter adapter = new PoliceDetailsAdapter(itemList, getApplicationContext());

                        try {

                            if(itemList!=null) {
                                rv_details_police.setAdapter(adapter);
                                rv_details_police.hasFixedSize();

                                rv_details_police.setLayoutManager(new LinearLayoutManager(getBaseContext()));


                                pd.setVisibility(View.GONE);
                            }

                            else{
                                Toast.makeText(getApplicationContext(), "Check Your Internet Connectivity!", Toast.LENGTH_SHORT).show();
                                pd.setVisibility(View.GONE);
                            }

                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "No Details found!", Toast.LENGTH_SHORT).show();
                            pd.setVisibility(View.GONE);
                        }
                    }
                });





    }

    void SearchPoliceDirectory(String name){

        DataProvider db = new DataProvider(getApplicationContext());
        String dist =  db.GetDistrictName();

        final String url = "http://192.168.43.104:8092/PoliceApp/SearchPoliceStations.aspx?DataFormat=name&name="+name;


        final ProgressBar pd;
        pd = (ProgressBar) findViewById(R.id.pb_details_police);
        pd.setVisibility(View.VISIBLE);



        Ion.with(getBaseContext())
                .load(url)
                .progressBar(pd)
                .as(new TypeToken<List<Details>>() {
                })
                .setCallback(new FutureCallback<List<Details>>() {

                    @Override
                    public void onCompleted(Exception e, List<Details> itemList) {
                        final PoliceDetailsAdapter adapter = new PoliceDetailsAdapter(itemList, getApplicationContext());

                        try {

                            if(itemList!=null) {
                                rv_details_police.setAdapter(adapter);
                                rv_details_police.hasFixedSize();

                                rv_details_police.setLayoutManager(new LinearLayoutManager(getBaseContext()));


                                pd.setVisibility(View.GONE);
                            }

                            else{
                                Toast.makeText(getApplicationContext(), "Check Your Internet Connectivity!", Toast.LENGTH_SHORT).show();
                                pd.setVisibility(View.GONE);
                            }

                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "No Details found!", Toast.LENGTH_SHORT).show();
                            pd.setVisibility(View.GONE);
                        }
                    }
                });





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


    @Override
    public boolean onCreateOptionsMenu(Menu menu ) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {


                if (newText.length() > 0)
                {


                    SearchPoliceDirectory(newText);


                }


                else {
                    GetPoliceDetails();
                }
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);



    }
}
