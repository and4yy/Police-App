package company.override.huzykamz.policecopuganda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class PoliceSelectionPanel extends AppCompatActivity {


    private CardView bd_registration_card,offence_registration_card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police_selection_panel);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Main Panel");

        bd_registration_card=(CardView)findViewById(R.id.bd_registration_card);
        offence_registration_card=(CardView)findViewById(R.id.offence_registration_card);


        bd_registration_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PoliceSelectionPanel.this,RegistrationTabs.class);
                startActivity(i);
            }
        });

        offence_registration_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PoliceSelectionPanel.this,OffenceRegister.class);
                startActivity(i);
            }
        });
    }
}
