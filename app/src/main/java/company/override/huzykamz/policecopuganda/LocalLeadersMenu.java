package company.override.huzykamz.policecopuganda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class LocalLeadersMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_leaders_menu);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Select any Leader");

    }





    public void OnMp(View v){

        Intent i = new Intent(LocalLeadersMenu.this,LocalLeaders.class);
        i.putExtra("POST","MP");
        startActivity(i);

    }

    public void OnRDC(View v){
        Intent i = new Intent(LocalLeadersMenu.this,LocalLeaders.class);

        i.putExtra("POST","RDC");
        startActivity(i);
    }

    public void OnLC5(View v){
        Intent i = new Intent(LocalLeadersMenu.this,LocalLeaders.class);
        i.putExtra("POST","LC5");
        startActivity(i);
    }

    public void OnMayor(View v){
        Intent i = new Intent(LocalLeadersMenu.this,LocalLeaders.class);
        i.putExtra("POST","MAYOR");
        startActivity(i);
    }

    public void OnLC3(View v){
        Intent i = new Intent(LocalLeadersMenu.this,LocalLeaders.class);
        i.putExtra("POST","LC3");
        startActivity(i);
    }

    public void OnLC2(View v){
        Intent i = new Intent(LocalLeadersMenu.this,LocalLeaders.class);
        i.putExtra("POST","LC2");
        startActivity(i);
    }


    public void OnLC1(View v){
        Intent i = new Intent(LocalLeadersMenu.this,LocalLeaders.class);
        i.putExtra("POST","LC1");
        startActivity(i);
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
