package company.override.huzykamz.policecopuganda;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;


public class FileUploadAudio extends AppCompatActivity {

    private static final int PICK_FILE_REQUEST = 1;
    private static final String TAG = MainActivity.class.getSimpleName();
    private String selectedFilePath;
    private String SERVER_URL = "http://192.168.43.104:8093/PoliceApp/FileUpload.aspx";
    ImageView ivAttachment;
    private String UPLOAD_URL ="http://192.168.43.104:8092/PoliceApp/ImageUpload.aspx";
    Button bUpload;
    TextView tvFileName;
    ProgressDialog dialog;
    EditText information_crimes_file_edt,phone_edt_video;
    Map<String, String> params;
    private  double longitude , latitude;
    private Spinner spinner_crime_file;
    private TrackGPS gps;
    Uri selectedFileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_upload);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Audio Upload");

        ivAttachment = (ImageView) findViewById(R.id.ivAttachment);
        bUpload = (Button) findViewById(R.id.b_upload);
        tvFileName = (TextView) findViewById(R.id.tv_file_name);
        String crimes_list [] = {"Other Illegal Offence","Harassment","Corruption","Domestic Violence"};



        information_crimes_file_edt=(EditText)findViewById(R.id.information_crimes_file_edt);
        phone_edt_video=(EditText) findViewById(R.id.phone_edt_video);
        spinner_crime_file=(Spinner)findViewById(R.id.spinner_crime_file);

        ArrayAdapter<String> crimeadap = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,crimes_list);
        crimeadap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_crime_file.setAdapter(crimeadap);


        ivAttachment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
            }
        });

        bUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedFileUri != null){

                    Upload2(selectedFileUri);


                }else{
                    Toast.makeText(FileUploadAudio.this,"Please choose a File First",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void showFileChooser() {
        Intent intent = new Intent();
        //sets the select file to all types of files
        intent.setType("audio/*");
        //allows to select data and return it
        intent.setAction(Intent.ACTION_GET_CONTENT);
        //starts new activity to select file and return data
        startActivityForResult(Intent.createChooser(intent,"Choose File to Upload.."),PICK_FILE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == PICK_FILE_REQUEST){
                if(data == null){
                    //no data present
                    return;
                }


                selectedFileUri = data.getData();
                selectedFilePath = FilePath.getPath(this,selectedFileUri);
                Log.i(TAG,"Selected File Path:" + selectedFilePath);

                if(selectedFilePath != null && !selectedFilePath.equals("")){
                    tvFileName.setText(selectedFilePath);
                }else{
                    Toast.makeText(this,"Cannot upload file to server",Toast.LENGTH_SHORT).show();
                }
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

    // uisng ion to upload





    //android upload file to server.

    //android upload file to server



    public void Upload2(final Uri filePath ) {

        String path = FilePath.getPath(this, filePath);
        final String information = information_crimes_file_edt.getText().toString();
        final String phone = phone_edt_video.getText().toString();

        final ProgressDialog pd;
        pd = new ProgressDialog(FileUploadAudio.this);
        pd.setMessage("Uploading Audio...");
        pd.setCancelable(false);
        pd.show();


        final File file = new File(Uri.parse(path).toString());




        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String CrimeDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String CrimeTime = new SimpleDateFormat("HH:mm:ss").format(new Date());
        String f = ""+ file;


         String URL = Uri.parse(SERVER_URL).buildUpon()
                 .appendQueryParameter("information",information)
                 .appendQueryParameter("phone",phone)
                 .appendQueryParameter("crimedate",CrimeDate)
                 .appendQueryParameter("crimetime",CrimeTime)
                 .appendQueryParameter("filename",f)
                 .build().toString();

        Ion.with(FileUploadAudio.this)
                .load("POST",URL)
                .progressDialog(pd)


                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {


                        //     Toast.makeText(getApplicationContext(),""+file,Toast.LENGTH_LONG).show();

                        System.out.println("Error  Here " + result);
                        // Toast.makeText(getApplicationContext(), "Exception : " + e, Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                        // Toast.makeText(getApplicationContext(),""+e,Toast.LENGTH_LONG).show();


                        pd.dismiss();


                    }
                });
    }

    public int uploadFile(final String selectedFilePath){

        int serverResponseCode = 0;

        HttpURLConnection connection;
        DataOutputStream dataOutputStream;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";


        int bytesRead,bytesAvailable,bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
        File selectedFile = new File(selectedFilePath);


        String[] parts = selectedFilePath.split("/");
        final String fileName = parts[parts.length-1];

        if (!selectedFile.isFile()){
            dialog.dismiss();

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tvFileName.setText("Source File Doesn't Exist: " + selectedFilePath);
                }
            });
            return 0;
        }else{
            try{

                FileInputStream fileInputStream = new FileInputStream(selectedFile);
                URL url = new URL(SERVER_URL);
                connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);//Allow Inputs
                connection.setDoOutput(true);//Allow Outputs
                connection.setUseCaches(false);//Don't use a cached Copy
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Connection", "Keep-Alive");
                connection.setRequestProperty("ENCTYPE", "multipart/form-data");
                connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                connection.setRequestProperty("uploaded_file",selectedFilePath);
                connection.setRequestProperty("","");









                //creating new dataoutputstream
                dataOutputStream = new DataOutputStream(connection.getOutputStream());

                //writing bytes to data outputstream
                dataOutputStream.writeBytes(twoHyphens + boundary + lineEnd);
                dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\""
                        + selectedFilePath + "\"" + lineEnd);

                dataOutputStream.writeBytes(lineEnd);

                //returns no. of bytes present in fileInputStream
                bytesAvailable = fileInputStream.available();
                //selecting the buffer size as minimum of available bytes or 1 MB
                bufferSize = Math.min(bytesAvailable,maxBufferSize);
                //setting the buffer as byte array of size of bufferSize
                buffer = new byte[bufferSize];

                //reads bytes from FileInputStream(from 0th index of buffer to buffersize)
                bytesRead = fileInputStream.read(buffer,0,bufferSize);

                //loop repeats till bytesRead = -1, i.e., no bytes are left to read
                while (bytesRead > 0){
                    //write the bytes read from inputstream
                    dataOutputStream.write(buffer,0,bufferSize);
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable,maxBufferSize);
                    bytesRead = fileInputStream.read(buffer,0,bufferSize);
                }

                dataOutputStream.writeBytes(lineEnd);
                dataOutputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                serverResponseCode = connection.getResponseCode();
                String serverResponseMessage = connection.getResponseMessage();

                Log.i(TAG, "Server Response is: " + serverResponseMessage + ": " + serverResponseCode);

                //response code of 200 indicates the server status OK
                if(serverResponseCode == 200){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvFileName.setText("File Upload completed.\n\n You can see the uploaded file here: \n\n" + "http://coderefer.com/extras/uploads/"+ fileName);
                        }
                    });
                }

                //closing the input and output streams
                fileInputStream.close();
                dataOutputStream.flush();
                dataOutputStream.close();



            } catch (FileNotFoundException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(FileUploadAudio.this,"File Not Found",Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (MalformedURLException e) {
                e.printStackTrace();
                Toast.makeText(FileUploadAudio.this, "URL error!", Toast.LENGTH_SHORT).show();

            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(FileUploadAudio.this, "Cannot Read/Write File!", Toast.LENGTH_SHORT).show();
            }
            dialog.dismiss();
            return serverResponseCode;
        }

    }





}