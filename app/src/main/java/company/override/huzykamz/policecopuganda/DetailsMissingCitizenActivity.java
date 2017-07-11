package company.override.huzykamz.policecopuganda;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import adapter.MissingCitizenAdapter;

public class DetailsMissingCitizenActivity extends AppCompatActivity {


    String name= "";
    String age ="";
    String address="";
    String details="";
    String image ="";
    String contact ="";
    Intent i;
    TextView nametxt, agetxt,addresstxt, detailstxt,contacttxt;
    ImageView img_details, details_missing_img;
    private String Image_URl= "http://192.168.43.104:8092/LostImages/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item__missing_citizen);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Missing Citizen");

        nametxt= (TextView)findViewById(R.id.nametxt);
        agetxt =(TextView)findViewById(R.id.age_txt);
        addresstxt =(TextView)findViewById(R.id.addresstxt);
        detailstxt =(TextView)findViewById(R.id.details_txt);
        img_details =(ImageView) findViewById(R.id.img_details);
        details_missing_img=(ImageView)findViewById(R.id.details_missing_img);
        i= getIntent();
        image = i.getStringExtra("Image");
        name = i.getStringExtra("Name");
        age =i.getStringExtra("Age");
        address = i.getStringExtra("Address");
        details = i.getStringExtra("Details");
        contact =i.getStringExtra("Call");

        nametxt.setText(name);
        agetxt.setText(age);
        addresstxt.setText(address);
        detailstxt.setText(details);
        contacttxt.setText(contact);
        Picasso.with(DetailsMissingCitizenActivity.this).load(Image_URl+image).into(img_details);

        details_missing_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                try {

                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + contact));
                    startActivity(callIntent);
                }
                catch (SecurityException e){


                    e.printStackTrace();
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
