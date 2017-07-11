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

import adapter.PoliceDetailsAdapter;
import adapter.WantedAdapter;
import model.Details;
import model.Wanted;

public class WantedList extends AppCompatActivity {
    public RecyclerView rv_wanted;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wanted_list);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Wanted List");

        rv_wanted = (RecyclerView)findViewById(R.id.rv_wanted);
        rv_wanted.hasFixedSize();
        rv_wanted.setLayoutManager(new LinearLayoutManager(this));
        GetWantedList();

    }

    void GetWantedList(){

        final String url = "http://192.168.43.104:8092/PoliceApp/Default.aspx?DataFormat=WantedList";


        final ProgressBar pd;
        pd = (ProgressBar) findViewById(R.id.progressBar_wanted);
        pd.setVisibility(View.VISIBLE);



        Ion.with(getBaseContext())
                .load(url)
                .progressBar(pd)
                .as(new TypeToken<List<Wanted>>() {
                })
                .setCallback(new FutureCallback<List<Wanted>>() {

                    @Override
                    public void onCompleted(Exception e, List<Wanted> itemList) {
                        final WantedAdapter adapter = new WantedAdapter(getApplicationContext(),itemList);

                        try {

                            if(itemList!= null) {
                                rv_wanted.setAdapter(adapter);
                                rv_wanted.hasFixedSize();

                                rv_wanted.setLayoutManager(new LinearLayoutManager(getBaseContext()));


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
