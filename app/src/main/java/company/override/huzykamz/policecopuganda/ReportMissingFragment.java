package company.override.huzykamz.policecopuganda;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

import static android.app.Activity.RESULT_OK;
import static java.security.AccessController.getContext;

/**
 * Created by HUZY_KAMZ on 3/7/2017.
 */

public class ReportMissingFragment extends Fragment {
    private static final int GALLERY_REQUEST = 1;
    private Spinner msSpinner;
    private EditText name,age,address, height, color, type, description;
    private Button send_missing_info_bt;
    private ImageView image_select_missing;
    private Bitmap bitmap;
    private String UPLOAD_URL ="http://192.168.43.104:8092/PoliceApp/ReportMissing.aspx";
    String   selectedFilePath;
    public ReportMissingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_video_fragment);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.report_missing_fragment, container, false);


        String list_missing [] = {"Missing Person","Missing Item"};
        msSpinner =(Spinner) v.findViewById(R.id.spinner_missing_type);
        name =(EditText)v.findViewById(R.id.name_edt);
        age =(EditText)v.findViewById(R.id.age_edt);
        address =(EditText)v.findViewById(R.id.address_edt);
        height =(EditText)v.findViewById(R.id.height_edt);
        color =(EditText)v.findViewById(R.id.color_edt);
        type =(EditText)v.findViewById(R.id.type_edt);
        image_select_missing =(ImageView)v.findViewById(R.id.image_select_missing);

        //setting on click Listener

        image_select_missing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFileChooser();
            }
        });

        description =(EditText)v.findViewById(R.id.description_edt);
        send_missing_info_bt=(Button)v.findViewById(R.id.send_missing_info_bt);
        ArrayAdapter<String> list_Adap = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,list_missing);
        msSpinner.setAdapter(list_Adap);

        send_missing_info_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               onUploading();
            }
        });



        msSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position)
                {
                    case 0:

                        type.setVisibility(View.GONE);
                        address.setVisibility(View.VISIBLE);
                        height.setVisibility(View.VISIBLE);
                        age.setVisibility(View.VISIBLE);


                        break;
                    case 1:

                        type.setVisibility(View.VISIBLE);
                        address.setVisibility(View.GONE);
                        height.setVisibility(View.GONE);
                        age.setVisibility(View.GONE);

                        break;

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filePath);
                //Setting the Bitmap to ImageView
                image_select_missing.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }


    private void onUploading() {

        final ProgressDialog loading = ProgressDialog.show(getActivity(),"Uploading...","Please wait...",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Disimissing the progress dialog
                        loading.dismiss();
                        //Showing toast message of the response
                        Toast.makeText(getActivity(), s , Toast.LENGTH_LONG).show();


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Dismissing the progress dialog
                        loading.dismiss();

                        //Showing toast
                        Toast.makeText(getActivity(), volleyError+"", Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Converting Bitmap to String
                selectedFilePath = getStringImage(bitmap);
                String Name = name.getText().toString();
                String Age = age.getText().toString();
                String Address = address.getText().toString();
                String Height = height.getText().toString();
                String Color = color.getText().toString();
                String Type = type.getText().toString();
                String Description = description.getText().toString();
                String Category = msSpinner.getSelectedItem().toString();
                String ImageName = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());




                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());



                Map<String, String> params = new Hashtable<String, String>();

                params.put("Image", selectedFilePath);
                params.put("ImageName", timeStamp);
                params.put("Name", Name);
                params.put("Age", Age);
                params.put("Address", Address);
                params.put("Height",Height);
                params.put("Color",Color);
                params.put("Type",Type);
                params.put("Description",Description);
                params.put("Category",Category);
                params.put("Color",Color);



                //returning parameters
                return params;

            }
        };

        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

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
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), GALLERY_REQUEST);
    }
    }

