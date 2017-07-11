package company.override.huzykamz.policecopuganda;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.List;

import adapter.NationalIdAdapter;
import adapter.TrafficSignAdapter;
import model.NationalID_Model;
import model.TrafficSignModel;

public class TrafficSigns extends AppCompatActivity {

    RecyclerView rv_traffic_signs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traffic_signs);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Traffic Signs");
        rv_traffic_signs =(RecyclerView)findViewById(R.id.rv_traffic_signs);

        OnLoad();

    }



    void  OnLoad()
    {




        final ProgressDialog pd = new ProgressDialog(TrafficSigns.this);
        pd.setMessage("Fetching Data ... ");

        pd.show();

        // 192.168.43.104
        final String url = "http://192.168.43.104:8092/PoliceApp/TrafficSigns.aspx?DataFormat=Signs";

        Ion.with(TrafficSigns.this)
                .load(url)
                .progressDialog(pd)
                .as(new TypeToken<List<TrafficSignModel>>() {
                })
                .setCallback(new FutureCallback<List<TrafficSignModel>>() {

                    @Override
                    public void onCompleted(Exception e, List<TrafficSignModel> itemList) {
                        try {

                            if(itemList!=null) {
                                final TrafficSignAdapter adapter = new TrafficSignAdapter(getApplicationContext(), itemList);


                                rv_traffic_signs.setAdapter(adapter);
                                rv_traffic_signs.hasFixedSize();
                                rv_traffic_signs.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


                                pd.dismiss();
                            }
                            else {

                                Toast.makeText(getApplicationContext(), "Make sure Your Connection is good ", Toast.LENGTH_SHORT).show();
                                pd.dismiss();
                            }
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "No Internet Connection!", Toast.LENGTH_SHORT).show();
                            pd.dismiss();
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
