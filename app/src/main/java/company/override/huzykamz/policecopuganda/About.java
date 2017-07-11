package company.override.huzykamz.policecopuganda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Calendar;

public class About extends AppCompatActivity {


    TextView all_rights_txt ;

    String Year ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("About");
        all_rights_txt =(TextView)findViewById(R.id.all_rights_txt);

        Calendar calendar = Calendar.getInstance();
        int year_int= calendar.get(Calendar.YEAR);
        Year = String.valueOf(year_int);

        all_rights_txt.setText("All rights Reserved © "+Year);




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
