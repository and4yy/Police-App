package company.override.huzykamz.policecopuganda;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class ClearChargesActivity extends AppCompatActivity {

    private EditText mobile_no;
    private TextView amount_charged_txt;
    private Button clear_charges_bt;
    String Amount ="" ;

     String AmountTwo = "";
    String TafficReceiptNo ="";
    String Name ="";
    String Tax = "";
    String Plate ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clear_charges);


        mobile_no =(EditText)findViewById(R.id.mobile_no);
        amount_charged_txt =(TextView)findViewById(R.id.amount_charged_txt);
        clear_charges_bt=(Button)findViewById(R.id.clear_charges_bt);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Clear Vehicle Charges ");

        Intent i = getIntent();
        if(i!=null){

            //DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
          //  DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();

           // symbols.setGroupingSeparator(' ');
           // formatter.setDecimalFormatSymbols(symbols);



            try {
                Amount = i.getStringExtra("charge");
                Name = i.getStringExtra("name");
                AmountTwo = i.getStringExtra("charge");
                Plate = i.getStringExtra("platenumber");
                Tax = i.getStringExtra("tax");
                int a = Integer.parseInt(Amount);


                DecimalFormat decimalFormat = new DecimalFormat("#,###");
                String numberAsString = decimalFormat.format(a);


                System.out.println("Results : "+ numberAsString);
                amount_charged_txt.setText(""+numberAsString+" UGX");
                //oast.makeText(getApplicationContext(),""+ numberAsString,Toast.LENGTH_LONG).show();


            }
            catch (NumberFormatException exx){


                System.out.println("Show me "+ exx);
            }

            //amount_charged_txt.setText(formatter.format(a));


            TafficReceiptNo= i.getStringExtra("trafficreceiptno");
            Name = i.getStringExtra("name");

        }


        clear_charges_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Register();

                Intent i = new Intent(ClearChargesActivity.this,Receipt.class);
                i.putExtra("name",Name);
                i.putExtra("platenumber",Plate);
                i.putExtra("amount",AmountTwo);
                i.putExtra("phone",mobile_no.getText().toString());
                i.putExtra("tax",Tax);
                i.putExtra("trafficreceiptno",TafficReceiptNo);

                startActivity(i);
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


   public String  ChangeFirstZero(){

       String  Mobile = mobile_no.getText().toString();
       if (Mobile.startsWith("0")){

           Mobile = "+256"+Mobile.substring(1,Mobile.length()-1);

       }


       return  Mobile;
   }


    public void Register()
    {
        final ProgressDialog pd;
        pd = new ProgressDialog(ClearChargesActivity.this);
        pd.setMessage("Processing Request...");
        pd.setCancelable(false);
        pd.show();
        final String url="https://app.beyonic.com/api/collectionrequests";

        double Amount =  Double.parseDouble(AmountTwo);


        String PayInstructions="";
        if(ChangeFirstZero().contains("+25677") || ChangeFirstZero().contains("+25678"))
        {
            PayInstructions="EA Mobile Directory has requested for payment of "+"UGX"+" " +
                    ""+Amount+". to "+getIntent().getStringExtra("reason")+" from you. Dial *165# > Goods & Services > Enter BEYONIC >" +
                    " Enter Amount > PIN > then Confirm. Request Expires in 24 hours";
        }
        else  if(ChangeFirstZero().contains("+25670") || ChangeFirstZero().contains("+25675"))
        {
            PayInstructions="EA Mobile Directory has requested for payment of "+"UGX"+" " +
                    ""+Amount+". to "+"clearing a Traffic Receipt "+TafficReceiptNo+" from you. Dial *185# > 4. Goods & Services > 9. Others >" +
                    " Enter Business No: 998998 > Amount > Your User Name as Reference > Your PIN  then Confirm. Request Expires in 24 hours";
        }

        JsonObject json = new JsonObject();
        json.addProperty("firstname", Name);
        json.addProperty("phonenumber",mobile_no.getText().toString());
        json.addProperty("amount",Amount);
        json.addProperty("currency","UGX");
        json.addProperty("description", "Traffic Fine Payment");
        json.addProperty("success_message", "Thanks for your payment.");
        json.addProperty("send_instructions", "True");
        json.addProperty("instructions", PayInstructions);

        Ion.with(getApplicationContext())
                .load(url)
                .progressDialog(pd)
                .addHeader("Content-Type", "application/json")
                .setHeader("Authorization", "Token 265174548d4baadaeed063ab60652f486d022bcf")
                .setJsonObjectBody(json)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        if (e != null) {
                            Toast.makeText(getBaseContext(), "Data : " + e.getStackTrace(), Toast.LENGTH_LONG).show();
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    pd.dismiss();
                                }
                            });
                            Toast.makeText(getBaseContext(), "Transaction Initiated. Check SMS for Approvals", Toast.LENGTH_LONG).show();
                        }
                        pd.dismiss();
                    }
                });
    }

}
