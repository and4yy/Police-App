package company.override.huzykamz.policecopuganda;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class SingleNewsActivity extends AppCompatActivity {


    private TextView headlines_singel_text, details_single_txt, date_details;
    private String Headlines,Details_,ImageNews, date_txt;
    private ImageView news_image_;
    // pc ip 192.168.43.104
    private static String PHOTO_BASE_URL ="http://192.168.43.104:8092/COOPERP/NewsImages/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_single_news);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("News Details");

        headlines_singel_text=(TextView) findViewById(R.id.headlines_singel_txt);
        details_single_txt =(TextView) findViewById(R.id.details_single_txt);
        date_details =(TextView) findViewById(R.id.date_details);
        news_image_ =(ImageView)findViewById(R.id.news_image_details) ;

        Intent inn = getIntent();
        if(inn!= null) {
            Headlines = inn.getStringExtra("headlines");
            Details_ = inn.getStringExtra("details");
            date_txt = inn.getStringExtra("date");

            ImageNews = inn.getStringExtra("image");
            headlines_singel_text.setText(Headlines);
            details_single_txt.setText(Details_);
            date_details.setText(date_txt);

            //Toast.makeText(getApplicationContext(),""+Details_, Toast.LENGTH_SHORT).show();

            Picasso.with(SingleNewsActivity.this).load(PHOTO_BASE_URL+ImageNews).fit().into(news_image_);



        }

        else{

            headlines_singel_text.setText("Null");
        }






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
