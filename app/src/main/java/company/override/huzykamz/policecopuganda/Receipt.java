package company.override.huzykamz.policecopuganda;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class Receipt extends AppCompatActivity {


    private TextView receipt_name,
            receipt_platenumber,receipt_amountcharged,
            receipt_tax,receipt_totalamount,
            receipt_phone,receipt_chargesheet;
    private String Name="";
    private String PlateNo="";
    private String Amount = "";
    private String Tax="";
    private String TotalAmount ="";
    private String Phone= "";
    private Button receipt_bt;
    private String ChargeSheet="";

    double total_amount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);

        receipt_name =(TextView)findViewById(R.id.receipt_name);
        receipt_platenumber=(TextView)findViewById(R.id.receipt_platenumber);
        receipt_amountcharged=(TextView)findViewById(R.id.receipt_amountcharged);
        receipt_tax=(TextView)findViewById(R.id.receipt_tax);
        receipt_totalamount=(TextView)findViewById(R.id.receipt_totalamount);
        receipt_phone =(TextView)findViewById(R.id.receipt_phone);
        receipt_chargesheet=(TextView)findViewById(R.id.receipt_chargesheet);
        receipt_bt =(Button)findViewById(R.id.receipt_bt);



        Intent i = getIntent();
        if (i!=null){

            Name = i.getStringExtra("name");
            PlateNo = i.getStringExtra("platenumber");
            Amount = i.getStringExtra("amount");
            Tax = i.getStringExtra("tax");
            Phone = i.getStringExtra("phone");
            ChargeSheet =i.getStringExtra("trafficreceiptno");


            double amount = Double.parseDouble(Amount);
            double tax_from_string = Double.parseDouble(Tax);
            double tax = amount * tax_from_string;


             total_amount = tax + amount;

            receipt_tax.setText(""+tax+" UGX ");
            receipt_name.setText(Name);
            receipt_platenumber.setText(PlateNo);
            receipt_amountcharged.setText(Amount+" UGX ");
            receipt_totalamount.setText(""+total_amount+" UGX ");
            receipt_phone.setText(ChangeFirstZero());
            receipt_chargesheet.setText(ChargeSheet);


        }


        receipt_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();
               // finish();
            }
        });


    }



    public String  ChangeFirstZero(){

        String  Mobile = Phone;
        if (Mobile.startsWith("0")){

            Mobile = "+256"+Mobile.substring(1,Mobile.length());

        }


        return  Mobile;
    }


    public void Register()
    {
        final ProgressDialog pd;
        pd = new ProgressDialog(Receipt.this);
        pd.setMessage("Processing Request...");
        pd.setCancelable(false);
        pd.show();
        final String url="https://app.beyonic.com/api/collectionrequests";





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
                    ""+Amount+". to "+"clearing a Traffic Receipt  from you. Dial *185# > 4. Goods & Services > 9. Others >" +
                    " Enter Business No: 998998 > Amount > Your User Name as Reference > Your PIN  then Confirm. Request Expires in 24 hours";
        }

        JsonObject json = new JsonObject();
        json.addProperty("firstname", Name);
        json.addProperty("phonenumber",ChangeFirstZero());
        json.addProperty("amount",total_amount);
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
                            Toast.makeText(getBaseContext(), "Please check your Internet Connectivity", Toast.LENGTH_LONG).show();
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
                        finish();
                    }
                });
    }
}
