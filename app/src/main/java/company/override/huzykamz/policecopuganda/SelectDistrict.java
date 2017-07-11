package company.override.huzykamz.policecopuganda;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.List;

import adapter.LocalLeadersAdapter;
import model.Districts;
import model.LocalLeaderModel;

public class SelectDistrict extends AppCompatActivity {

    ProgressBar progressBar_districts;
    Spinner spinner_districts;
    Button choose_district_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_district);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Choose Your District");


        GetDistricts();

        DataProvider db = new DataProvider(getApplicationContext());
        List<String> items = db.GetListOfDistricts();

        spinner_districts = (Spinner) findViewById(R.id.spinner_districts);
        progressBar_districts =(ProgressBar)findViewById(R.id.progressBar_districts);
        choose_district_bt =(Button) findViewById(R.id.choose_district_bt);

        choose_district_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog pd = new ProgressDialog(SelectDistrict.this);
                pd.setMessage("Changing District");
                pd.show();


                String id = spinner_districts.getSelectedItem().toString();
                DataProvider db = new DataProvider(getApplicationContext());
                db.UpdateDistrict(spinner_districts.getSelectedItem().toString());
               // Toast.makeText(getApplicationContext(),""+id,Toast.LENGTH_LONG).show();

                Intent i = new Intent(SelectDistrict.this,MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);


                Toast.makeText(SelectDistrict.this,""+db.GetDistrictName()
                 +" District Has Been Selected", Toast.LENGTH_SHORT).show();

                System.out.println("Error "+ db.OnInsertDistrict());

                pd.dismiss();




            }
        });

        ArrayAdapter<String> resdap = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,items);

        resdap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Attach the Adapter.
        spinner_districts.setAdapter(resdap);







    }



    void GetDistricts(){




        progressBar_districts= (ProgressBar) findViewById(R.id.progressBar_districts);

        final String url = "http://192.168.43.104:8092/PoliceApp/Default.aspx?DataFormat=Districts";


        progressBar_districts.setVisibility(View.VISIBLE);



        Ion.with(getBaseContext())
                .load(url)
                .progressBar(progressBar_districts)
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
                                progressBar_districts.setVisibility(View.GONE);



                            }
                            else {
                                Toast.makeText(getApplicationContext(), "Check Your Internet Connection Please!", Toast.LENGTH_SHORT).show();
                                progressBar_districts.setVisibility(View.GONE);
                            }

                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "No Details found!", Toast.LENGTH_SHORT).show();
                            progressBar_districts.setVisibility(View.GONE);
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
