package company.override.huzykamz.policecopuganda;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.List;

import adapter.MissingCitizenAdapter;
import adapter.NationalIdAdapter;
import adapter.PermitVerifyAdapter;
import model.MissingCitizen;
import model.NationalID_Model;
import model.PermitVerifyModel;


/**
 * Created by HUZY_KAMZ on 2/11/2017.
 */

public class Permit_Verification_Fragment extends Fragment {

    // ProgressBar pd;
    private RecyclerView rv;
    private EditText permit_verify_edt;
    private Button search_track_bt;
    public Permit_Verification_Fragment() {
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


        View v = inflater.inflate(R.layout.permit_verification, container, false);

        rv = (RecyclerView) v.findViewById(R.id.rv_permit_verify);
        // pd = (ProgressBar) v.findViewById(R.id.progressBar_citizen);
        search_track_bt =(Button)v.findViewById(R.id.permit_verify_bt);
        permit_verify_edt =(EditText)v.findViewById(R.id.permit_verify_edt);
        rv.hasFixedSize();
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        search_track_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LoadData();
            }
        });


        return v;
    }







    public void LoadData(){

        String TextInserted= permit_verify_edt.getText().toString();
        // 192.168.43.104
        final String url = "http://192.168.43.104:8092/PoliceApp/LoadPermit.aspx?DataFormat=PermitNo&PermitNo="+TextInserted;

        final  ProgressDialog pd = new ProgressDialog(getContext());
        pd.setMessage("Fetching Data ... ");
        pd.show();



        Ion.with(Permit_Verification_Fragment.this)
                .load(url)
                .progressDialog(pd)
                .as(new TypeToken<List<PermitVerifyModel>>() {
                })
                .setCallback(new FutureCallback<List<PermitVerifyModel>>() {

                    @Override
                    public void onCompleted(Exception e, List<PermitVerifyModel> itemList) {
                        try {

                            if(itemList!=null) {
                                final PermitVerifyAdapter adapter = new PermitVerifyAdapter(itemList, getContext());


                                rv.setAdapter(adapter);
                                rv.hasFixedSize();
                                rv.setLayoutManager(new LinearLayoutManager(getContext()));


                                pd.dismiss();
                            }
                            else {
                                Toast.makeText(getContext(), "This Permit is not Valid", Toast.LENGTH_SHORT).show();
                                pd.dismiss();
                            }
                        }
                        catch (Exception ex){
                            Toast.makeText(getContext(), "No Internet Connection!", Toast.LENGTH_SHORT).show();
                            pd.dismiss();
                        }
                    }
                });




    }




}
