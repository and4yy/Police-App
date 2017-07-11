package company.override.huzykamz.policecopuganda;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

/**
 * Created by Huzy_Kamz on 4/20/2017.
 */

public class BirthRegistrationFragment extends Fragment {

    ProgressBar pd;
    private RecyclerView rv;
    private EditText birth_fathers_name_txt, birth_mother_name_txt,
            birth_fathers_id_txt,birth_mother_national_id_txt,birth_village_txt,
            birth_parish_txt,birth_county_txt,birth_hospital_edt;
private Spinner spinner_birth_registration;
    private Button birth_registration_bt;
    public BirthRegistrationFragment() {
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


        View v = inflater.inflate(R.layout.birth_registration_fragment, container, false);
        birth_fathers_name_txt =(EditText)v.findViewById(R.id.birth_fathers_name_edt);
        birth_mother_name_txt =(EditText)v.findViewById(R.id.birth_mother_name_edt);
        birth_fathers_id_txt =(EditText)v.findViewById(R.id.birth_fathers_id_edt);
        birth_mother_national_id_txt =(EditText)v.findViewById(R.id.birth_mother_national_id_edt);
        birth_village_txt =(EditText)v.findViewById(R.id.birth_village_edt);
        birth_parish_txt =(EditText)v.findViewById(R.id.birth_parish_edt);
        birth_county_txt=(EditText)v.findViewById(R.id.birth_county_edt);
        birth_hospital_edt =(EditText)v.findViewById(R.id.birth_hospital_edt);
        spinner_birth_registration=(Spinner)v.findViewById(R.id.spinner_birth_registration);
        birth_registration_bt =(Button)v.findViewById(R.id.birth_registration_bt);
        String list_missing [] = {"Male","Female"};
        ArrayAdapter<String> list_Adap = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,list_missing);
        spinner_birth_registration.setAdapter(list_Adap);


        birth_registration_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BirthRegistration();
            }
        });



        return v;
    }



    void BirthRegistration() {
        //computer ip address 192.168.43.104:82
        final String FatherName = birth_fathers_name_txt.getText().toString();
        final String MotherName = birth_mother_name_txt.getText().toString();
        final String Father_ID = birth_fathers_id_txt.getText().toString();
        final String Mother_Id = birth_mother_national_id_txt.getText().toString();
        final String Village = birth_village_txt.getText().toString();
        final String Parish = birth_parish_txt.getText().toString();
        final String County = birth_county_txt.getText().toString();
        final String Gender = spinner_birth_registration.getSelectedItem().toString();
        final String Hospital = birth_hospital_edt.getText().toString();



        if (FatherName.equals("")&&MotherName.equals("")&&Father_ID.equals("")&&
                Mother_Id.equals("")&&Village.equals("")
                &&Parish.equals("")&&County.equals("")&&Hospital.equals("")) {
            Toast.makeText(getContext(), "Please make sure that all the fields are not Empty !!!", Toast.LENGTH_LONG).show();

        } else


            {
                final ProgressDialog pd;
                pd = new ProgressDialog(getContext());
                pd.setMessage("Birth Registering...");
                pd.setCancelable(false);
                pd.show();
            final String url_ = "http://192.168.43.104:8092/PoliceApp/BirthRegistration.aspx?Sex=" + Gender + "&MotherName=" + MotherName + "&FatherName=" + FatherName + "" +
                    "&MotherId=" + Mother_Id + "&FatherId=" + Father_ID + "&Village=" + Village + "&Parish=" + Parish + "&County=" + County + "&Hospital=" + Hospital + "";

            Ion.with(BirthRegistrationFragment.this)
                    .load(url_)
                    .progressDialog(pd)
                    .asString()
                    .setCallback(new FutureCallback<String>() {
                        @Override
                        public void onCompleted(Exception e, String result) {
                            try {

                                if (result.toString().equals("Birth Successfully Registered")) {

                                    Toast.makeText(getContext(), "Birth Registered Successfully", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(getContext(), "Birth Not Successfully Registered " + result, Toast.LENGTH_LONG).show();
                                }
                            } catch (Exception ex) {

                                Toast.makeText(getContext(), "No Internet Connection", Toast.LENGTH_LONG).show();


                            }

                            pd.dismiss();
                            birth_fathers_name_txt.getText().clear();
                            birth_mother_name_txt.getText().clear();
                            birth_fathers_id_txt.getText().clear();
                            birth_mother_national_id_txt.getText().clear();
                            birth_village_txt.getText().clear();
                            birth_parish_txt.getText().clear();
                            birth_county_txt.getText().clear();


                        }
                    });

        }
    }
}





