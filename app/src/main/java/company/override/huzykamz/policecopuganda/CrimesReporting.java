package company.override.huzykamz.policecopuganda;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

public class CrimesReporting extends AppCompatActivity {

    // IP 192.168.43.104

    private String UPLOAD_URL ="http://192.168.43.104:8092/PoliceApp/ImageUpload.aspx";
    private Spinner spinner;
    private ImageView image_capture, image_gallery, image_incident
            ,image_video,record_crime;
    private static final int CAMERA_REQUEST = 1888;
    Uri imageUri = null;
    private static final int GALLERY_REQUEST = 1;
    private static final int RESULT_CODE_COMPRESS_VIDEO = 1;
    Uri mVideoURI = null;
    private Bitmap bitmap;
    private EditText information_crimes_edt,contact_crimes;

    private static final int SELECT_VIDEO = 3;
    private String selectedFilePath;
    private  double longitude , latitude;
    private TrackGPS gps;
    Map<String, String> params;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crimes_reporting);

        String crimes_list [] = {"Other Illegal Offence","Harassment","Corruption","Domestic Violence"};
        spinner= (Spinner) findViewById(R.id.spinner_crime);
        image_capture = (ImageView)findViewById(R.id.image_capture);
        image_gallery =(ImageView) findViewById(R.id.image_gallery);
        image_incident =(ImageView) findViewById(R.id.image_incident);
        image_video =(ImageView) findViewById(R.id.image_video);
        record_crime= (ImageView) findViewById(R.id.record_crime);

        information_crimes_edt =(EditText) findViewById(R.id.information_crimes_edt);
        contact_crimes=(EditText) findViewById(R.id.contact_crimes);

        ArrayAdapter<String> crimeadap = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,crimes_list);
        crimeadap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(crimeadap);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Report an Incident");

         image_gallery.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 showFileChooser();

             }
         });
        image_capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });
        image_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(CrimesReporting.this,FileUploadVideo.class);
                startActivity(i);

            }
        });
        record_crime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CrimesReporting.this,FileUploadAudio.class);
                startActivity(i);
            }
        });

    }


    public void OnRecord(View v){

        Intent i = new Intent(CrimesReporting.this,CrimeRecording.class);
        startActivity(i);
    }

    public void OnSubmitCrime(View v)
    {

       // onUploading();

        gps = new TrackGPS(CrimesReporting.this);


        if(gps.canGetLocation()){


            longitude = gps.getLongitude();
            latitude = gps .getLatitude();

            //Toast.makeText(getApplicationContext(),"Longitude:"+Double.toString(longitude)+"\nLatitude:"+Double.toString(latitude),Toast.LENGTH_SHORT).show();

            onUploading(Double.toString(latitude),Double.toString(longitude));


            //contact_crimes.setText("");
            //information_crimes_edt.setText("");



        }
        else
        {

            gps.showSettingsAlert();
        }

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            bitmap = (Bitmap) data.getExtras().get("data");
            image_incident.setImageBitmap(bitmap);
        }
      else    if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //Setting the Bitmap to ImageView
                image_incident.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }

    private void onUploading(final String lati, final String longi) {

        final ProgressDialog loading = ProgressDialog.show(this,"Uploading...","Please wait...",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Disimissing the progress dialog
                        loading.dismiss();
                        //Showing toast message of the response
                        Toast.makeText(CrimesReporting.this, s , Toast.LENGTH_LONG).show();

                        information_crimes_edt.setText("");
                        contact_crimes.setText("");

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Dismissing the progress dialog
                        loading.dismiss();

                        //Showing toast
                      Toast.makeText(CrimesReporting.this, "An error has occurred , please try Again", Toast.LENGTH_LONG).show();
                        System.out.println("Error here " + volleyError);

                        try{
                            System.out.println("Error here " + volleyError);

                        }
                        catch (Exception e){

                        }
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Converting Bitmap to String
                try {
                    information_crimes_edt =(EditText) findViewById(R.id.information_crimes_edt);
                    contact_crimes=(EditText) findViewById(R.id.contact_crimes);
                    selectedFilePath = getStringImage(bitmap);

                    String contact = contact_crimes.getText().toString();
                    String InformationCrime = information_crimes_edt.getText().toString();
                    String CrimeType = spinner.getSelectedItem().toString();

                    //  Toast.makeText(getApplicationContext(),""+CrimeType,Toast.LENGTH_LONG).show();
                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                    String CrimeDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                    String CrimeTime = new SimpleDateFormat("HH:mm:ss").format(new Date());





                        params = new Hashtable<String, String>();

                        params.put("ImagePic", selectedFilePath);
                        params.put("ImageName", timeStamp);
                        params.put("Contact", contact);
                        params.put("CrimeType", CrimeType);
                        params.put("Information", InformationCrime);
                        params.put("CrimeDate", CrimeDate);
                        params.put("CrimeTime", CrimeTime);
                        params.put("Latitude", lati);
                        params.put("Longitude", longi);







                }

                catch (RuntimeException exx){

                    Toast.makeText(CrimesReporting.this, "Attach an Image Please  and also make sure that the  " +
                            " Information Field is not Empty." +
                            "", Toast.LENGTH_LONG).show();
                }



                //returning parameters}
                return params;

            }

        };

        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(stringRequest);

    }



    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your GPS seems to be disabled, turn it on to submit Fast, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
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
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), GALLERY_REQUEST);
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
