/*package notifications;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import company.override.huzykamz.policecopuganda.R;


public class Alert extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.alerts);
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        if(null!= extras && getIntent().getExtras().containsKey("message")){

            TextView message =(TextView) findViewById(R.id.msg);
            message.setText(extras.getString("message"));
        }
    }
}
*/