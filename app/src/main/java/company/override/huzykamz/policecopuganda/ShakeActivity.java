package company.override.huzykamz.policecopuganda;

import android.app.ProgressDialog;
import android.content.Context;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;

import android.net.Uri;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import shake.Shake;

import static company.override.huzykamz.policecopuganda.Login.EmergencyName;
import static company.override.huzykamz.policecopuganda.Login.EmergencyOne;
import static company.override.huzykamz.policecopuganda.Login.EmergencyThree;
import static company.override.huzykamz.policecopuganda.Login.EmergencyTwo;
import static company.override.huzykamz.policecopuganda.Login.MyPhoneNumeber;

public class ShakeActivity extends AppCompatActivity {
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private Shake mShakeDetector;
    private Switch aSwitch;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;
    String  message ="Hello";
    String phoneNo ="0704594180";
    SharedPreferences pref;
    private static final String IS_LOGIN = "IsLoggedIn";
    View v;
    private CoordinatorLayout coordinatorLayout;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake);

        aSwitch = (Switch) findViewById(R.id.switch1);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Shake Your Phone");



// ShakeDetector initialization
                 mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
                 mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


                 mShakeDetector = new Shake();
                 mShakeDetector.setOnShakeListener(new Shake.OnShakeListener() {
            @Override
            public void onShake(int count) {


                if (aSwitch.isChecked()){


                 //   Toast.makeText(getApplicationContext(),"The notification  has been sent successfully",Toast.LENGTH_LONG).show();

                    //sendSMSMessage();
                    SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);

                    String MyPhone = pref.getString(MyPhoneNumeber, null);
                    String EmgOne = pref.getString(EmergencyOne, null);
                    String EmgTwo = pref.getString(EmergencyTwo, null);
                    String EmgThree =  pref.getString(EmergencyThree, null);

                    SendMySMS();

                }
                else {
                    SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
                    String MyPhone = pref.getString(MyPhoneNumeber, "-");
                    String EmgOne = pref.getString(EmergencyOne, "-");
                    String EmgTwo = pref.getString(EmergencyTwo, "-");
                    String EmgThree =  pref.getString(EmergencyThree, "-");


                    Toast.makeText(getApplicationContext(),"Turn on the switch please",Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(),"MyPhone "+MyPhone+
                            "EmOne"+ EmgOne+"EmgTwo "+EmgTwo+"Emg Three"
                            +EmgThree+" Boolean ",Toast.LENGTH_LONG).show();

                }


            }
        });




    }








    // SMS sending


    public void SendMySMS() {
        String Shed = "N";
        String schedDate="-",schedTime="-";
        final ProgressDialog pd;
        Date sDate=null;

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        String MyNum =  pref.getString(MyPhoneNumeber, null);
        String EmOne =  pref.getString(EmergencyOne,null);
        String EmTwo = pref.getString(EmergencyTwo,null);
        String EmThree =pref.getString(EmergencyThree,null);
        String Name = pref.getString(EmergencyName,null);

        String Numbers = EmOne+","+EmTwo+","+EmThree;
        pd = new ProgressDialog(ShakeActivity.this);
        try
        {
          //  EditText txtSender = (EditText) findViewById(R.id.txtSender);
          //  EditText txtPhoneContacts = (EditText) findViewById(R.id.txtPhoneContacts);
          //  EditText txtMessage = (EditText) findViewById(R.id.txtMessage);
            String URL;
            pd.setMessage("Sending...");
            pd.setCancelable(false);
            pd.show();



                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm-dd-yyyy");
                schedDate = simpleDateFormat.format(calendar.getTime());
                schedTime="12:00";


            SharedPreferences websetting_link = getSharedPreferences("smslink", 0);
            SharedPreferences cur_username = getSharedPreferences("cur_username", 0);
            URL = Uri.parse("http://196.43.172.28/Mobile/MobileApi.aspx").buildUpon()
                    .appendQueryParameter("Action", "SendSMS")
                    .appendQueryParameter("unm", "police")
                    .appendQueryParameter("Receipients",  Numbers)
                    .appendQueryParameter("Message",
                            "Testing Message , Am in danger and i need your immediate help , please " +
                                    " help....   " +
                                    "Message sent by "+ Name+
                                    " Uganda PoliceApp ")
                    .appendQueryParameter("upass", "1234")
                    .appendQueryParameter("Scheduled", Shed)
                    .appendQueryParameter("Date", schedDate)
                    .appendQueryParameter("Time", schedTime)
                    .build().toString();
            final String mDate=schedDate;
            Ion.with(getBaseContext())
                    .load(URL)
                    .progressDialog(pd)
                    .asString()
                    .setCallback(new FutureCallback<String>() {
                        @Override
                        public void onCompleted(Exception e, String result) {
                            try {
                                String responseValue = result;
                                Toast.makeText(getBaseContext(), responseValue, Toast.LENGTH_LONG).show();

                            } catch (Exception ex) {

                                Toast.makeText(getBaseContext(), "No Internet. Check and try again", Toast.LENGTH_LONG).show();

                            }
                            pd.dismiss();
                        }
                    });
        }catch (Exception e)
        {
            Toast.makeText(getBaseContext(), "Error!" +e.getMessage()+" - Date=: "+sDate, Toast.LENGTH_LONG).show();
            pd.dismiss();
        }

        //

    }


    @Override
    public void onResume() {
        super.onResume();
        // Add the following line to register the Session Manager Listener onResume
        mSensorManager.registerListener(mShakeDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        // Add the following line to unregister the Sensor Manager onPause
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.updatesos, menu);//Menu Resource, Menu
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                break;


        }

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.sos_menu) {


            Intent i = new Intent(ShakeActivity.this,UpdatingShakeRegister.class);
            startActivity(i);
        }
        else if (id ==R.id.sos_view){

            Intent i = new Intent(ShakeActivity.this,ViewSosContacts.class);
            startActivity(i);
        }


        return super.onOptionsItemSelected(item);
    }
}
