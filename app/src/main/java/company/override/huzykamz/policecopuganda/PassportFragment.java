package company.override.huzykamz.policecopuganda;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.List;

import adapter.PassportAdapter;
import adapter.PermitVerifyAdapter;
import model.Passport;
import model.PermitVerifyModel;

/**
 * Created by Huzy_Kamz on 4/12/2017.
 */

public class PassportFragment extends Fragment {

    // ProgressBar pd;
    private RecyclerView rv;
    private Button search_track_bt;
    private EditText passport_verify_edt;

    public PassportFragment() {
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


        View v = inflater.inflate(R.layout.passport_verification, container, false);

        rv = (RecyclerView) v.findViewById(R.id.rv_passport_verify);
        // pd = (ProgressBar) v.findViewById(R.id.progressBar_citizen);
        search_track_bt = (Button) v.findViewById(R.id.passport_verify_bt);

        passport_verify_edt =(EditText)v.findViewById(R.id.passport_verify_edt);
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


    public void LoadData() {

        String TextInserted= passport_verify_edt.getText().toString();
        // 192.168.43.104
        final String url = "http://192.168.43.104:8092/PoliceApp/PassportLoad.aspx?DataFormat=PassPortNo&PassPortNo="+TextInserted;

        final ProgressDialog pd = new ProgressDialog(getContext());
        pd.setMessage("Fetching Data ... ");
        pd.show();


        Ion.with(PassportFragment.this)
                .load(url)
                .progressDialog(pd)
                .as(new TypeToken<List<Passport>>() {
                })
                .setCallback(new FutureCallback<List<Passport>>() {

                    @Override
                    public void onCompleted(Exception e, List<Passport> itemList) {
                        try {

                            if (itemList != null) {
                                final PassportAdapter adapter = new PassportAdapter(itemList, getContext());


                                rv.setAdapter(adapter);
                                rv.hasFixedSize();
                                rv.setLayoutManager(new LinearLayoutManager(getContext()));


                                pd.dismiss();
                            } else {
                                Toast.makeText(getContext(), "This Passport No. is not Valid", Toast.LENGTH_LONG).show();
                                pd.dismiss();
                            }
                        } catch (Exception ex) {
                            Toast.makeText(getContext(), "No Internet Connection!", Toast.LENGTH_SHORT).show();
                            pd.dismiss();
                        }
                    }
                });


    }
}

