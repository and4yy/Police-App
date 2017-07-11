package company.override.huzykamz.policecopuganda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Diaspora extends AppCompatActivity {


    private EditText name_diaspora_edt;
    private Spinner spinner_age_diaspora,spinner_gender_diaspora,
            spinner_marital_status_diaspora;
    private Button one_next_diaspora_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diaspora);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Diaspora Registration");

        name_diaspora_edt=(EditText)findViewById(R.id.name_diaspora_edt);
        spinner_age_diaspora=(Spinner)findViewById(R.id.spinner_age_diaspora);
        spinner_gender_diaspora =(Spinner)findViewById(R.id.spinner_gender_diaspora);
        spinner_marital_status_diaspora =(Spinner)findViewById(R.id.spinner_marital_status_diaspora);
        one_next_diaspora_bt =(Button)findViewById(R.id.one_next_diaspora_bt);

        // Gender Spinner
        String list_missing [] = {"Male","Female"};
        ArrayAdapter<String> list_Adap = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,list_missing);
        list_Adap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_gender_diaspora.setAdapter(list_Adap);


        //Age Spinner
        String age_listing [] = {"1","2","3","4","5","6","7","8","9","10"
                ,"11","12","13","14","15","16","17","18","19","20","21","22","22","23","24","25","26","27",
                "28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50",
                "51","52","53","54","55","56","57","58","59","60","61","62","63","64","65","66","67","68","69","70","71","72",
                "73","74","75","76","77","78","79","80","81","82","83","84","85","86","87","88","89",
                "90","91","92","93","94","95","96","97","98","99","100","101","102","103","104","105"};
        ArrayAdapter<String> Age_Adap = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,age_listing);
        Age_Adap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_age_diaspora.setAdapter(Age_Adap);

        //Marital Status

        String status_listing [] = {"Married ","Single"};
        ArrayAdapter<String> StatusAdap = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,status_listing);
        StatusAdap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_marital_status_diaspora.setAdapter(StatusAdap);




       //final String Name = name_diaspora_edt.getText().toString().trim();
      final   String Age = spinner_age_diaspora.getSelectedItem().toString();
       final String Gender = spinner_gender_diaspora.getSelectedItem().toString();
       final String MaritalStatus = spinner_marital_status_diaspora.getSelectedItem().toString();






        one_next_diaspora_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String Name = name_diaspora_edt.getText().toString().trim();
                if(Name.matches("")){


                    Toast.makeText(getApplicationContext(),"Make sure that you have filled your name please !!!",Toast.LENGTH_LONG).show();


                }

                else{



                    Intent i = new Intent(Diaspora.this,DiasporaTwo.class);
                    i.putExtra("Name",Name);
                    i.putExtra("Age",Age);
                    i.putExtra("Gender",Gender);
                    i.putExtra("MaritalStatus",MaritalStatus);
                    startActivity(i);
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
