package company.override.huzykamz.policecopuganda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DiasporaTwo extends AppCompatActivity {


    private EditText passport_no_diaspora_edt,national_id_diaspoara_edt,country_diaspora_edt,
    city_diaspora_edt;

    String Name ="";
    String Age = "";
    String Gender ="";
    String MaritalStatus ="";
    private Button two_next_diaspora_bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diaspora_two);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Diaspora Registration");

        passport_no_diaspora_edt=(EditText)findViewById(R.id.passport_no_diaspora_edt);
        national_id_diaspoara_edt=(EditText)findViewById(R.id.national_id_diaspoara_edt);
        country_diaspora_edt=(EditText)findViewById(R.id.country_diaspora_edt);
        city_diaspora_edt=(EditText)findViewById(R.id.city_diaspora_edt);
        two_next_diaspora_bt=(Button)findViewById(R.id.two_next_diaspora_bt);

       final Intent inn = getIntent();




        two_next_diaspora_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(inn!=null) {

                    Name = inn.getStringExtra("Name");
                    Age = inn.getStringExtra("Age");
                    Gender = inn.getStringExtra("Gender");
                    MaritalStatus = inn.getStringExtra("MaritalStatus");
                }
                final String Passport = passport_no_diaspora_edt.getText().toString().trim();
                final  String ID = national_id_diaspoara_edt.getText().toString().trim();
                final   String Country = country_diaspora_edt.getText().toString().trim();
                final  String City = city_diaspora_edt.getText().toString().trim();

                if (Passport.matches("")){

                    Toast.makeText(getApplicationContext(),"Please make sure that Passport is filled !!!",Toast.LENGTH_LONG).show();
                }

                else if(ID.matches("")){
                    Toast.makeText(getApplicationContext(),"Please make sure that National Id  is filled !!!",Toast.LENGTH_LONG).show();

                }

                else if (Country.matches("")){
                    Toast.makeText(getApplicationContext(),"Please make sure that Country  is filled !!!",Toast.LENGTH_LONG).show();
                }
                else if (City.matches("")){
                    Toast.makeText(getApplicationContext(),"Please make sure that City  is filled !!!",Toast.LENGTH_LONG).show();
                }



                    Intent i = new Intent(DiasporaTwo.this,DiasporaThree.class);
                    i.putExtra("Name",Name);
                    i.putExtra("Age",Age);
                    i.putExtra("Gender",Gender);
                    i.putExtra("MaritalStatus",MaritalStatus);
                    i.putExtra("Passport",Passport);
                    i.putExtra("ID",ID);
                    i.putExtra("City",City);
                    i.putExtra("Country",Country);
                    startActivity(i);


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
