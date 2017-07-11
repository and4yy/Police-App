package company.override.huzykamz.policecopuganda;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import static company.override.huzykamz.policecopuganda.Login.EmergencyName;
import static company.override.huzykamz.policecopuganda.Login.EmergencyOne;
import static company.override.huzykamz.policecopuganda.Login.EmergencyThree;
import static company.override.huzykamz.policecopuganda.Login.EmergencyTwo;
import static company.override.huzykamz.policecopuganda.Login.MyPhoneNumeber;

public class ViewSosContacts extends AppCompatActivity {

    private TextView view_sos_names,
            view_sos_phonenumber,view_sos_rescuenumberone,view_sos_rescuenumbertwo,
    view_sos_rescuenumberthree;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_sos_contacts);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Saved SOS contacts");

        SharedPreferences sharedpreferences = getApplicationContext().getSharedPreferences("MyPref", 0);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        view_sos_names = (TextView) findViewById(R.id.view_sos_names);
        view_sos_phonenumber =(TextView)findViewById(R.id.view_sos_phonenumber);
        view_sos_rescuenumberone =(TextView)findViewById(R.id.view_sos_rescuenumberone);
        view_sos_rescuenumbertwo =(TextView)findViewById(R.id.view_sos_rescuenumbertwo);
        view_sos_rescuenumberthree =(TextView)findViewById(R.id.view_sos_rescuenumberthree);





        String MyPhone = sharedpreferences.getString(MyPhoneNumeber, null);
        String EmgOne = sharedpreferences.getString(EmergencyOne, null);
        String EmgTwo = sharedpreferences.getString(EmergencyTwo, null);
        String EmgThree =  sharedpreferences.getString(EmergencyThree, null);
        String Name = sharedpreferences.getString(EmergencyName,null);

        view_sos_names.setText(Name);
        view_sos_phonenumber.setText(MyPhone);
        view_sos_rescuenumberone.setText(EmgOne);
        view_sos_rescuenumbertwo.setText(EmgTwo);
        view_sos_rescuenumberthree.setText(EmgThree);





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
