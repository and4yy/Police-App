package company.override.huzykamz.policecopuganda;

import android.content.Intent;
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

import adapter.LocalLeadersAdapter;
import adapter.WantedAdapter;
import model.LocalLeaderModel;
import model.Wanted;

public class LocalLeaders extends AppCompatActivity {


    private RecyclerView rv_local_leaders;
    private ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_leader);

        rv_local_leaders = (RecyclerView)findViewById(R.id.rv_local_leaders);
        pb= (ProgressBar) findViewById(R.id.progressBar_local_leaders);


        DataProvider db = new DataProvider(getApplicationContext());
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("DISTRICT :"+db.GetDistrictName());

        rv_local_leaders.hasFixedSize();
        rv_local_leaders.setLayoutManager(new LinearLayoutManager(this));

        GetLocalLeadersList();
    }


    void GetLocalLeadersList(){



        String postinitial ="";


        Intent i = getIntent();
        postinitial = i.getStringExtra("POST");

        DataProvider db = new DataProvider(getBaseContext());

        String District  = db.GetDistrictName();


        Toast.makeText(getApplicationContext(),"District Show  : "+ District,Toast.LENGTH_LONG).show();

        Toast.makeText(getApplicationContext(),"POST : "+postinitial,Toast.LENGTH_LONG).show();
        pb= (ProgressBar) findViewById(R.id.progressBar_local_leaders);

        final String url = "http://192.168.43.104:8092/PoliceApp/LocalLeaders.aspx?postinitial="+postinitial+"&district="+District;




        pb.setVisibility(View.VISIBLE);



        Ion.with(getBaseContext())
                .load(url)
                .progressBar(pb)
                .as(new TypeToken<List<LocalLeaderModel>>() {
                })
                .setCallback(new FutureCallback<List<LocalLeaderModel>>() {

                    @Override
                    public void onCompleted(Exception e, List<LocalLeaderModel> itemList) {
                        final LocalLeadersAdapter adapter = new LocalLeadersAdapter(getApplicationContext(),itemList);

                        try {

                            if(itemList!= null) {
                                rv_local_leaders.setAdapter(adapter);
                                rv_local_leaders.hasFixedSize();

                                rv_local_leaders.setLayoutManager(new LinearLayoutManager(getBaseContext()));


                                pb.setVisibility(View.GONE);
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "Check Your Internet Connection Please!", Toast.LENGTH_SHORT).show();
                                pb.setVisibility(View.GONE);
                            }

                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "No Details found!", Toast.LENGTH_SHORT).show();
                            pb.setVisibility(View.GONE);
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
