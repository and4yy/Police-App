package company.override.huzykamz.policecopuganda;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import adapter.LocalLeadersAdapter;

public class DetailsLocalLeader extends AppCompatActivity {

    private Button call,email;
    private TextView name_txt, village_txt, email_txt, phone_txt;
    private ImageView imgdetails;

    String Email ="";
    String Name="";
    String Phone="";
    String Image ="";
    Intent i ;
    private String Image_URl= "http://192.168.43.104:8092/COOPERP/LocalLeaders/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_local_leader);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("INFORMATION");

        call =(Button) findViewById(R.id.call_details_bt);
        email =(Button)findViewById(R.id.email_details_bt);
        name_txt = (TextView)findViewById(R.id.name_details_txt);
        village_txt =(TextView)findViewById(R.id.village_details_txt);
        email_txt = (TextView) findViewById(R.id.email_details_txt);
        phone_txt =(TextView) findViewById(R.id.phone_details_txt);
        imgdetails =(ImageView)findViewById(R.id.img_details_local);


        i= getIntent();

        if (i!=null) {
            Email = i.getStringExtra("Email");
            Name = i.getStringExtra("Name");
            Image = i.getStringExtra("Image");
            Phone = i.getStringExtra("Call");
            Image =i.getStringExtra("Image");



            name_txt.setText(Name);
            email_txt.setText(Email);
            phone_txt.setText(Phone);

            Picasso.with(DetailsLocalLeader.this).load(Image_URl+Image).into(imgdetails);

        }





        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                try {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + Phone));
                    startActivity(callIntent);
                }
                catch (SecurityException e){
                    e.printStackTrace();
                }


            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{Email});
                i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
                i.putExtra(Intent.EXTRA_TEXT   , "body of email");
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(DetailsLocalLeader.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
