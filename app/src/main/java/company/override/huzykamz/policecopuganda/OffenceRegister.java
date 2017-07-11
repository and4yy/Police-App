package company.override.huzykamz.policecopuganda;

import android.app.ProgressDialog;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.List;

import model.Districts;
import model.TrafficOffenceModel;

public class OffenceRegister extends AppCompatActivity {

    private EditText offence_platenumber_edt, offence_permitnumber_edt
            ;
    private Spinner spinner_offence;
    private CheckBox checkBoxmaximum,checkBoxminmum;
    private Button two_next_diaspora_bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offence_register);
        offence_platenumber_edt = (EditText)findViewById(R.id.offence_platenumber_edt);
        offence_permitnumber_edt =(EditText)findViewById(R.id.offence_permitnumber_edt);
        spinner_offence =(Spinner)findViewById(R.id.spinner_offence);
        checkBoxmaximum=(CheckBox)findViewById(R.id.checkBoxmaximum);
        checkBoxminmum =(CheckBox)findViewById(R.id.checkBoxminmum);
        two_next_diaspora_bt =(Button)findViewById(R.id.two_next_diaspora_bt);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Register Traffic Offence");

        GetOffences();


        DataProvider db = new DataProvider(getApplicationContext());
        List<String> items = db.GetOffenceList();

        ArrayAdapter<String> resdap = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,items);

        resdap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Attach the Adapter.
        spinner_offence.setAdapter(resdap);



        two_next_diaspora_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Submit();
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
    void GetOffences(){




        final ProgressBar progressBar_D= (ProgressBar) findViewById(R.id.pb_offence);

        final String url = "http://192.168.43.104:8092/PoliceApp/Default.aspx?DataFormat=Offence";


        progressBar_D.setVisibility(View.VISIBLE);



        Ion.with(getBaseContext())
                .load(url)
                .progressBar(progressBar_D)
                .as(new TypeToken<List<TrafficOffenceModel>>() {
                })
                .setCallback(new FutureCallback<List<TrafficOffenceModel>>() {

                    @Override
                    public void onCompleted(Exception e, final List<TrafficOffenceModel> itemList) {


                        try {

                            if(itemList!= null) {

                                final List<String> items = new ArrayList<String>();



                                for ( int i = 0; i < itemList.size(); i++) {
                                    items.add(itemList.get(i).getOffence());


                                    DataProvider DB = new DataProvider(getBaseContext());
                                    String Offence = itemList.get(i).getOffence();
                                    String Id = itemList.get(i).getId();

                                    DB.InsertOffence(Offence,Id);


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


    void Submit(){

        String strMessage = "";

        if(checkBoxmaximum.isChecked()){
            strMessage+="maximum ";
        }
        if(checkBoxminmum.isChecked()){
            strMessage+="minimum ";
        }

        String OFFENCE_PLATENUMBER = offence_platenumber_edt.getText().toString();
        String OFFENCE_PERMITNUMBER = offence_permitnumber_edt.getText().toString();
        int  OFFENCE_SPINNER_OFFENCE = spinner_offence.getSelectedItemPosition();

        String OffenceId = ""+ OFFENCE_SPINNER_OFFENCE;

        if (OFFENCE_PERMITNUMBER.equals("")){
            Toast.makeText(getApplicationContext(),"Please insert Plate Number and Permit Number",Toast.LENGTH_LONG).show();

        }

        else if (OFFENCE_PERMITNUMBER.equals("")){
            Toast.makeText(getApplicationContext(),"Please insert Permit  Number",Toast.LENGTH_LONG).show();
        }


        else if(checkBoxminmum.isChecked()&&checkBoxmaximum.isChecked()){
            Toast.makeText(getApplicationContext(),"You cant check both Maximum and Minimum once , select one ",Toast.LENGTH_LONG).show();
        }
        else if(!checkBoxminmum.isChecked()||!checkBoxmaximum.isChecked()){
            Toast.makeText(getApplicationContext(),"Check atleast one of them  ",Toast.LENGTH_LONG).show();
        }
        else {

            final ProgressDialog pd;
            pd = new ProgressDialog(OffenceRegister.this);
            pd.setMessage("Registering...");
            pd.setCancelable(false);
            pd.show();
            final String url = Uri.parse("http://192.168.43.104:8092/PoliceApp/AddingTrafficChargeSheet.aspx").buildUpon()
                    .appendQueryParameter("Regnumber", OFFENCE_PLATENUMBER)
                    .appendQueryParameter("PermitNumber", OFFENCE_PERMITNUMBER)
                    .appendQueryParameter("OffenceId", OffenceId)
                    .appendQueryParameter("FineStatus", strMessage)
                    .appendQueryParameter("Username", "-")

                    .build().toString();

            Ion.with(OffenceRegister.this)
                    .load(url)
                    .progressDialog(pd)
                    .asString()
                    .setCallback(new FutureCallback<String>() {
                        @Override
                        public void onCompleted(Exception e, String result) {
                            try {

                                if (result.toString().equals("Traffic Charge Successfully Registered")) {

                                    Toast.makeText(getApplicationContext(), " Traffic Charge Successfully Registered", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), " Not  Registered, Please try again " + result, Toast.LENGTH_LONG).show();

                                    System.out.println("Error " + result);
                                }
                            } catch (Exception ex) {

                                Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();


                            }

                            pd.dismiss();
                            offence_platenumber_edt.getText().clear();
                            offence_permitnumber_edt.getText().clear();


                        }
                    });

        }
    }
}
