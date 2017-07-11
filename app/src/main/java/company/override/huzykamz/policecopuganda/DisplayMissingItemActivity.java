package company.override.huzykamz.policecopuganda;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class DisplayMissingItemActivity extends AppCompatActivity {
    String name= "";
    String type ="";
    String color="";
    String details="";
    String image ="";
    String Contact ="";
    Intent i;
    TextView nametxt, typetxt,colortxt, detailstxt,details_contact_item;
    ImageView img_details_item,details_missingItem_img;
    private String Image_URl= "http://192.168.43.104:8092/LostImages/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_missing_item);


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Missing Item");

        nametxt =(TextView)findViewById(R.id.name_item);
        typetxt =(TextView) findViewById(R.id.type_item);
        colortxt =(TextView) findViewById(R.id.color_item);
        detailstxt =(TextView)findViewById(R.id.details_item);
        details_contact_item=(TextView)findViewById(R.id.details_contact_item);
        img_details_item =(ImageView)findViewById(R.id.img_details_item) ;
        details_missingItem_img=(ImageView)findViewById(R.id.details_missingItem_img);

        i = getIntent();

        name = i.getStringExtra("Name");
        type = i.getStringExtra("Type");
        color = i.getStringExtra("Color");
        details = i.getStringExtra("Details");
        image = i.getStringExtra("Image");
        Contact = i.getStringExtra("Call");


        nametxt.setText(name);
        typetxt.setText(type);
        detailstxt.setText(details);
        colortxt.setText(color);
        details_contact_item.setText(Contact);

        Picasso.with(DisplayMissingItemActivity.this).load(Image_URl+image).into(img_details_item);

        details_missingItem_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                try {

                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + Contact));
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
