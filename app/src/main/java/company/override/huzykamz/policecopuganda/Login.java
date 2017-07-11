package company.override.huzykamz.policecopuganda;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class Login extends AppCompatActivity {

    private EditText your_mobile_no_edt,emergency_mobile_no_three_edt;
    private EditText emergency_mobile_number_one_edt,emergency_mobile_no_two_edt,
            update_your_name_edt;
    private Button   register_sos_bt;
    SharedPreferences sharedpreferences;
    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String MyPREFERENCES = "MyPref" ;
    public static final String MyPhoneNumeber = "phonenumber";
    public static final String EmergencyOne = "emergencyone";
    public static final String EmergencyTwo = "emergencytwo";
    public static final String EmergencyName = "name";
    public static final String EmergencyThree = "emailKey";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Save Our Lives (SOS)");


        emergency_mobile_no_three_edt = (EditText)findViewById(R.id.emergency_mobile_no_three_edt);
        your_mobile_no_edt = (EditText)findViewById(R.id.your_mobile_no_edt);
        emergency_mobile_number_one_edt = (EditText)findViewById(R.id.emergency_mobile_number_one_edt);
        emergency_mobile_no_two_edt = (EditText)findViewById(R.id.emergency_mobile_no_two_edt);
        update_your_name_edt=(EditText)findViewById(R.id.update_your_name_edt);
        register_sos_bt = (Button) findViewById(R.id.register_sos_bt);

    //    sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);






        register_sos_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String PHONE  = your_mobile_no_edt.getText().toString();
                String EMERG_ONE  = emergency_mobile_number_one_edt.getText().toString();
                String EMERG_TWO  = emergency_mobile_no_two_edt.getText().toString();
                String EMERG_THREE  = emergency_mobile_no_three_edt.getText().toString();
                String NAME =update_your_name_edt.getText().toString();


                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
                SharedPreferences.Editor editor = pref.edit();




                // then we add new items

                editor.putString(MyPhoneNumeber, PHONE);
                editor.putString(EmergencyOne, EMERG_ONE);
                editor.putString(EmergencyTwo, EMERG_TWO);
                editor.putString(EmergencyThree, EMERG_THREE);
                editor.putString(EmergencyName, NAME);
                editor.putBoolean(IS_LOGIN,true);
                editor.commit();



                if (PHONE.equals("")) {

                    Toast.makeText(Login.this, "Your Mobile Number Field is Empty!!!", Toast.LENGTH_LONG).show();
                }

                else if (EMERG_ONE.equals("")) {
                    Toast.makeText(Login.this, "Your Emergency Phone One  Field is Empty!!!", Toast.LENGTH_LONG).show();
                }

                else if (EMERG_TWO.equals("")) {
                    Toast.makeText(Login.this, "Your Emergency Phone Two  Field is Empty!!!", Toast.LENGTH_LONG).show();
                }

                else if (EMERG_THREE.equals("")) {
                    Toast.makeText(Login.this, "Your Emergency Phone  Three  Field is Empty!!!", Toast.LENGTH_LONG).show();
                }
                else {


                  //  if (!isLoggedIn()) {
                        Intent i = new Intent(Login.this, ShakeActivity.class);
                        startActivity(i);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        // Add new Flag to start new Activity
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        Toast.makeText(Login.this, "Registered Your SOS", Toast.LENGTH_LONG).show();
                        finish();
                 //   }

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

    public boolean isLoggedIn(){
        return sharedpreferences.getBoolean(IS_LOGIN, false);
    }



}
