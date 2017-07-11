package company.override.huzykamz.policecopuganda;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class DiasporaThree extends AppCompatActivity {


    private EditText employer_diaspora_edt,homedistrict_diaspoara_edt,telephone_diaspora_edt,
            town_diaspora_edt;
    private Button two_next_diaspora_bt;
    String Name="";
    String Age = "";
    String Gender ="";
    String MariatlStatus ="";
    String PassportNo ="";
    String ID ="";
    String Country ="";
    String City ="";
    String Employer ="";
    String HomeDistrict ="";
    String Telephone ="";
    String Town ="";
    private Button register_diaspora;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diasopar_three);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Diaspora Registration");
        employer_diaspora_edt =(EditText)findViewById(R.id.employer_diaspora_edt);
        homedistrict_diaspoara_edt =(EditText)findViewById(R.id.homedistrict_diaspoara_edt);
        telephone_diaspora_edt=(EditText)findViewById(R.id.telephone_diaspora_edt);
        town_diaspora_edt=(EditText)findViewById(R.id.town_diaspora_edt);
        register_diaspora=(Button)findViewById(R.id.register_diaspora);


        Intent inn = getIntent();

        Name = inn.getStringExtra("Name");
        Age = inn.getStringExtra("Age");

        PassportNo =inn.getStringExtra("Passport");
        ID=inn.getStringExtra("ID");
        Country = inn.getStringExtra("Country");
        City = inn.getStringExtra("City");






        register_diaspora.setOnClickListener(new View.OnClickListener() {
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

    void Submit(){

       /* if(Employer.matches("")){

            Toast.makeText(getApplicationContext(),"Please make sure that Employer is filled !!!",Toast.LENGTH_LONG).show();
        }

        else if (HomeDistrict.matches("")){

            Toast.makeText(getApplicationContext(),"Please make sure that Home District is filled !!!",Toast.LENGTH_LONG).show();
        }
        else if (Telephone.matches("")){

            Toast.makeText(getApplicationContext(),"Please make sure that Telephone is filled !!!",Toast.LENGTH_LONG).show();
        }

        else if (Town.matches("")){

            Toast.makeText(getApplicationContext(),"Please make sure that Town District is filled !!!",Toast.LENGTH_LONG).show();
        }
*/

        //else {
            Intent inn = getIntent();
            Gender = inn.getStringExtra("Gender");
            MariatlStatus = inn.getStringExtra("MaritalStatus");

            Employer = employer_diaspora_edt.getText().toString();
            HomeDistrict = homedistrict_diaspoara_edt.getText().toString();
            Telephone = telephone_diaspora_edt.getText().toString();
            Town = town_diaspora_edt.getText().toString();

            final ProgressDialog pd;
            pd = new ProgressDialog(DiasporaThree.this);
            pd.setMessage("Registering...");
            pd.setCancelable(false);
            pd.show();
          //  final String url_ = "http://192.168.43.104:8092/PoliceApp/DiasporaRegistration.aspx?Name=" + Name + "&Age=" + Age + "&Gender=" + Gender + "" +
                //    "&MaritalStatus=" + MariatlStatus + "&Passport=" + PassportNo + "&ID=" + ID + "&City=" + City + "&Employer="
                 //   + Employer + "&HomeDistrict=" + HomeDistrict + "&Telephone=" + Telephone + "&Town=" + Town + "&Country=" + Country;

        final String url = Uri.parse("http://192.168.43.104:8092/PoliceApp/DiasporaRegistration.aspx").buildUpon()
                .appendQueryParameter("Name", Name)
                .appendQueryParameter("Age", Age)
                .appendQueryParameter("Gender", Gender)
                .appendQueryParameter("MaritalStatus", MariatlStatus)
                .appendQueryParameter("Passport", PassportNo)
                .appendQueryParameter("ID", ID)
                .appendQueryParameter("City", City)
                .appendQueryParameter("Employer", Employer)
                .appendQueryParameter("HomeDistrict", HomeDistrict)
                .appendQueryParameter("Telephone", Telephone)
                .appendQueryParameter("Town", Town)
                .appendQueryParameter("Country", Country)
                .build().toString();

            Ion.with(DiasporaThree.this)
                    .load(url)
                    .progressDialog(pd)
                    .asString()
                    .setCallback(new FutureCallback<String>() {
                        @Override
                        public void onCompleted(Exception e, String result) {
                            try {

                                if (result.toString().equals("Registration is successfull")) {

                                    Toast.makeText(getApplicationContext(), " Registered Successfully", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), " Not  Registered, Please try again " + result, Toast.LENGTH_LONG).show();

                                    System.out.println("Error " +result);
                                }
                            } catch (Exception ex) {

                                Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();


                            }

                            pd.dismiss();
                            employer_diaspora_edt.getText().clear();
                            homedistrict_diaspoara_edt.getText().clear();
                            telephone_diaspora_edt.getText().clear();
                            town_diaspora_edt.getText().clear();


                        }
                    });

        //}

    }
}
