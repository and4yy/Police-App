package company.override.huzykamz.policecopuganda;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Huzy_Kamz on 4/20/2017.
 */

public class DeathRegistrationFragment extends Fragment {



    private EditText deaths_name_edt, death_mother_name_edt,
            death_father_edt,death_cause_id_edt,death_national_id_edt;
    private Spinner spinner_death_age_registration;
    private static final int GALLERY_REQUEST = 1;
    private ImageView image_death_registration;
    private Bitmap bitmap;
    private Button death_registration_bt;
    public DeathRegistrationFragment() {
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


        View v = inflater.inflate(R.layout.activty_death_registration_fragment, container, false);
        deaths_name_edt =(EditText)v.findViewById(R.id.deaths_name_edt);
        death_mother_name_edt =(EditText)v.findViewById(R.id.death_mother_name_edt);
        death_father_edt =(EditText)v.findViewById(R.id.death_father_edt);
        death_cause_id_edt =(EditText)v.findViewById(R.id.death_cause_id_edt);
        death_national_id_edt=(EditText)v.findViewById(R.id.death_national_id_edt);
        spinner_death_age_registration=(Spinner)v.findViewById(R.id.spinner_death_age_registration);
        death_registration_bt =(Button)v.findViewById(R.id.death_registration_bt);
        image_death_registration=(ImageView)v.findViewById(R.id.image_death_registration);
        String list_missing [] = {"1","2","3","4","5","6","7","8","9","10"
                ,"11","12","13","14","15","16","17","18","19","20","21","22","22","23","24","25","26","27",
                "28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50",
                "51","52","53","54","55","56","57","58","59","60","61","62","63","64","65","66","67","68","69","70","71","72",
                "73","74","75","76","77","78","79","80","81","82","83","84","85","86","87","88","89",
                "90","91","92","93","94","95","96","97","98","99","100","101","102","103","104","105"};
        ArrayAdapter<String> list_Adap = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,list_missing);
        spinner_death_age_registration.setAdapter(list_Adap);


        death_registration_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BirthRegistration();
            }
        });

        image_death_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFileChooser();
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
                image_death_registration.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



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


    void BirthRegistration() {
        //computer ip address 192.168.43.104:82
        final String Name = deaths_name_edt.getText().toString();
        final String MotherName = death_mother_name_edt.getText().toString();
        final String FatherName = death_father_edt.getText().toString();
        final String Age = spinner_death_age_registration.getSelectedItem().toString();
        final String Cause = death_cause_id_edt.getText().toString();
        final String Id = death_national_id_edt.getText().toString();
        final String photo =getStringImage(bitmap);
        //selectedFilePath = getStringImage(bitmap);



        if (Name.equals("")&&MotherName.equals("")&&FatherName.equals("")&&
                Cause.equals("")&&Id.equals("")
                ) {
            Toast.makeText(getContext(), "Please make sure that all the fields are not Empty !!!", Toast.LENGTH_LONG).show();

        } else


        {
            final ProgressDialog pd;
            pd = new ProgressDialog(getContext());
            pd.setMessage("Death Registering...");
            pd.setCancelable(false);
            pd.show();
            final String url_ = "http://192.168.43.104:8092/PoliceApp/DeathRegistration.aspx?Name=" + Name + "&MotherName=" + MotherName + "&FatherName=" + FatherName + "" +
                    "&Age=" + Age + "&Cause=" + Cause + "&Id=" + Id + "&photo=" + photo ;

            Ion.with(DeathRegistrationFragment.this)
                    .load(url_)
                    .progressDialog(pd)
                    .asString()
                    .setCallback(new FutureCallback<String>() {
                        @Override
                        public void onCompleted(Exception e, String result) {
                            try {

                                if (result.toString().equals("Death Successfully Registered")) {

                                    Toast.makeText(getContext(), "Death Registered Successfully", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(getContext(), "Death Not Successfully Registered " + result, Toast.LENGTH_LONG).show();
                                }
                            } catch (Exception ex) {

                                Toast.makeText(getContext(), "No Internet Connection", Toast.LENGTH_LONG).show();


                            }

                            pd.dismiss();
                            deaths_name_edt.getText().clear();
                            death_mother_name_edt.getText().clear();
                            death_father_edt.getText().clear();
                            death_cause_id_edt.getText().clear();
                            death_national_id_edt.getText().clear();



                        }
                    });

        }
    }
}





