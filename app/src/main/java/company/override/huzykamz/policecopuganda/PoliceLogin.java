package company.override.huzykamz.policecopuganda;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class PoliceLogin extends AppCompatActivity {

    private Button police_admin_bt;
    private EditText police_id_edt,police_password_edt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police_login);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Police Admin");
        police_admin_bt= (Button)findViewById(R.id.police_admin_bt);
        police_password_edt=(EditText) findViewById(R.id.police_password_edt);
        police_id_edt=(EditText)findViewById(R.id.police_id_edt);

        police_admin_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                OnLoggingIn();

            }
        });
    }





    private void OnLoggingIn() {
        //computer ip address 192.168.43.104:82
        final String   username = police_id_edt.getText().toString();
        final   String    password = police_password_edt.getText().toString();

        Intent i  = getIntent();




       final  ProgressDialog pd;
        pd = new ProgressDialog(PoliceLogin.this);
        pd.setMessage("Logging in...");
        pd.setCancelable(false);
        pd.show();
        final String url_ = "http://192.168.43.104:8092/PoliceApp/Login.aspx?Username="+username+"&Password="+password;

        Ion.with(PoliceLogin.this)
                .load(url_)
                .progressDialog(pd)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {


                        if (result.toString().equals("Accepted")) {

                            //  myIntent.putExtra("username", username.getText().toString().toLowerCase());


                            Toast.makeText(PoliceLogin.this, "Logging in", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(PoliceLogin.this,PoliceSelectionPanel.class);
                            i.putExtra("USERNAME", username);
                            startActivity(i);

                        } else {
                            Toast.makeText(PoliceLogin.this, "Invalid User Name or Password " , Toast.LENGTH_LONG).show();
                        }



                        pd.dismiss();

                        police_password_edt.getText().clear();

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
