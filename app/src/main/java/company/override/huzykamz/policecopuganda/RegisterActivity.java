package company.override.huzykamz.policecopuganda;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private Spinner gender;
    private EditText username,personal_contact,password,e_contact_one,e_contact_two;
    private Button btnSignUp;
    private ImageView profilepic;
    private String UPLOAD_URL ="http://192.168.43.104:7777/PoliceApp/register/register.php";
    private Bitmap bitmap;
    private int PICK_IMAGE_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Register Here");

        gender =(Spinner) findViewById(R.id.spinner_sign_up);
        username =(EditText)findViewById(R.id.username_signup);
        personal_contact =(EditText)findViewById(R.id.personal_contact_signup);
        password =(EditText)findViewById(R.id.password_signup);
        e_contact_one =(EditText)findViewById(R.id.emergency_one_signup);
        e_contact_two =(EditText) findViewById(R.id.emergency_two_signup);
        btnSignUp= (Button) findViewById(R.id.btnSignUp);
        profilepic =(ImageView) findViewById(R.id.image_signup);

        String genderlist [] = {"Male", "Female"};
        ArrayAdapter<String> gender_Adap = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,genderlist);
        gender_Adap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(gender_Adap);

        profilepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSignUp();
            }
        });
    }

    private void onSignUp() {

//Showing the progress dialog
        final ProgressDialog loading = ProgressDialog.show(this,"Uploading...","Please wait...",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Disimissing the progress dialog
                        loading.dismiss();
                        //Showing toast message of the response
                        Toast.makeText(RegisterActivity.this, s , Toast.LENGTH_LONG).show();

                        Intent i = new Intent(RegisterActivity.this,Login.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Dismissing the progress dialog
                        loading.dismiss();

                        //Showing toast
                        Toast.makeText(RegisterActivity.this, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Converting Bitmap to String
                String image = getStringImage(bitmap);

                //Getting Image Name
                String Username = username.getText().toString().trim();
                String PersonalContact = personal_contact.getText().toString().trim();
                String Password = password.getText().toString().trim();
                String Em_one = e_contact_one.getText().toString().trim();
                String Em_two = e_contact_two.getText().toString().trim();
                String Gender = gender.getSelectedItem().toString();





                //Creating parameters
                Map<String,String> params = new Hashtable<String, String>();

                //Adding parameters
                params.put("username", Username);
                params.put("personal_contact", PersonalContact);
                params.put("password", Password);
                params.put("emergency_contact_one", Em_one);
                params.put("emergency_contact_two", Em_two);
                params.put("gender", Gender);
                params.put("photo",image);

                //returning parameters
                return params;
            }
        };

        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(stringRequest);


    }
    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //Setting the Bitmap to ImageView
                profilepic.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
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
