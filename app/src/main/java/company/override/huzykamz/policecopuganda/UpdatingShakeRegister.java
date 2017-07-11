package company.override.huzykamz.policecopuganda;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import adapter.MenuAdapter;

import static company.override.huzykamz.policecopuganda.Login.EmergencyName;
import static company.override.huzykamz.policecopuganda.Login.EmergencyOne;
import static company.override.huzykamz.policecopuganda.Login.EmergencyThree;
import static company.override.huzykamz.policecopuganda.Login.EmergencyTwo;
import static company.override.huzykamz.policecopuganda.Login.MyPhoneNumeber;

public class UpdatingShakeRegister extends AppCompatActivity {
  private EditText update_your_mobile_no_edt
          ,update_emergency_mobile_number_one_edt,update_emergency_mobile_no_two_edt
          ,updateemergency_mobile_no_three_edt, update_your_name_edt;
    private Button update_register_sos_bt;
    SharedPreferences sharedpreferences;
    private static final String IS_LOGIN = "IsLoggedIn";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updating_shake_register);


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Updating Contacts");

        update_register_sos_bt=(Button)findViewById(R.id.update_register_sos_bt);
        update_your_mobile_no_edt=(EditText)findViewById(R.id.update_your_mobile_no_edt);
        update_emergency_mobile_number_one_edt=(EditText)findViewById(R.id.update_emergency_mobile_number_one_edt);
        update_emergency_mobile_no_two_edt=(EditText)findViewById(R.id.update_emergency_mobile_no_two_edt);
        updateemergency_mobile_no_three_edt=(EditText)findViewById(R.id.updateemergency_mobile_no_three_edt);
        update_your_name_edt= (EditText)findViewById(R.id.update_your_name_edt);

        update_register_sos_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String PHONE  = update_your_mobile_no_edt.getText().toString();
                String EMERG_ONE  = update_emergency_mobile_number_one_edt.getText().toString();
                String EMERG_TWO  = update_emergency_mobile_no_two_edt.getText().toString();
                String EMERG_THREE  = updateemergency_mobile_no_three_edt.getText().toString();
                String NAME = update_your_name_edt.getText().toString();


                sharedpreferences = getApplicationContext().getSharedPreferences("MyPref", 0);
                SharedPreferences.Editor editor = sharedpreferences.edit();


                // First remove the existing items

                editor.remove(MyPhoneNumeber);
                editor.remove(EmergencyOne);
                editor.remove(EmergencyTwo);
                editor.remove(EmergencyThree);
                editor.remove(EmergencyName);
                editor.clear();
                editor.commit();

                // then we add new items

                editor.putString(MyPhoneNumeber, PHONE);
                editor.putString(EmergencyOne, EMERG_ONE);
                editor.putString(EmergencyTwo, EMERG_TWO);
                editor.putString(EmergencyThree, EMERG_THREE);
                editor.putString(EmergencyName,NAME);
                editor.putBoolean(IS_LOGIN,true);

                editor.commit();






                if (PHONE.equals("")) {

                    Toast.makeText(UpdatingShakeRegister.this, "Your Mobile Number Field is Empty!!!", Toast.LENGTH_LONG).show();
                }

                else if (EMERG_ONE.equals("")) {
                    Toast.makeText(UpdatingShakeRegister.this, "Your Emergency Phone One  Field is Empty!!!", Toast.LENGTH_LONG).show();
                }

                else if (EMERG_TWO.equals("")) {
                    Toast.makeText(UpdatingShakeRegister.this, "Your Emergency Phone Two  Field is Empty!!!", Toast.LENGTH_LONG).show();
                }

                else if (EMERG_THREE.equals("")) {
                    Toast.makeText(UpdatingShakeRegister.this, "Your Emergency Phone  Three  Field is Empty!!!", Toast.LENGTH_LONG).show();
                }
                else {


                    //  if (!isLoggedIn()) {
                    Intent i = new Intent(UpdatingShakeRegister.this, ShakeActivity.class);
                    startActivity(i);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    // Add new Flag to start new Activity
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Toast.makeText(UpdatingShakeRegister.this, "Contacts Updated Successfully", Toast.LENGTH_LONG).show();
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







}
