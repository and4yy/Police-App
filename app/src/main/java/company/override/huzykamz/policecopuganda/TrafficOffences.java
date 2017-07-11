package company.override.huzykamz.policecopuganda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.List;

import adapter.TrafficOffenceAdapter;
import adapter.WantedAdapter;
import model.TrafficOffenceModel;
import model.Wanted;

public class TrafficOffences extends AppCompatActivity {
    private RecyclerView rv_traffic_offence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traffic_offences);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Traffic Offences and Penalties");

        rv_traffic_offence = (RecyclerView)findViewById(R.id.rv_traffic_offence);
        rv_traffic_offence.hasFixedSize();
        rv_traffic_offence.setLayoutManager(new LinearLayoutManager(this));

        GetTrafficOffences();
    }



    void GetTrafficOffences(){

        final String url = "http://192.168.43.104:8092/PoliceApp/Default.aspx?DataFormat=Offence";


        final ProgressBar pd;
        pd = (ProgressBar) findViewById(R.id.progressBar_traffic_offence);
        pd.setVisibility(View.VISIBLE);



        Ion.with(getBaseContext())
                .load(url)
                .progressBar(pd)
                .as(new TypeToken<List<TrafficOffenceModel>>() {
                })
                .setCallback(new FutureCallback<List<TrafficOffenceModel>>() {

                    @Override
                    public void onCompleted(Exception e, List<TrafficOffenceModel> itemList) {
                        final TrafficOffenceAdapter adapter = new TrafficOffenceAdapter(getApplicationContext(),itemList);

                        try {

                            if(itemList!= null) {
                                rv_traffic_offence.setAdapter(adapter);
                                rv_traffic_offence.hasFixedSize();

                                rv_traffic_offence.setLayoutManager(new LinearLayoutManager(getBaseContext()));


                                pd.setVisibility(View.GONE);
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "Check Your Internet Please!", Toast.LENGTH_SHORT).show();
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
}
