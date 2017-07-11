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

import adapter.PassportTrackAdapter;

import model.Passport;


/**
 * Created by Huzy_Kamz on 4/18/2017.
 */

public class PassportTrackFragment extends Fragment {

    // ProgressBar pd;
    private RecyclerView rv;
    private EditText passport_verify_edt;
    private Button search_track_bt;

    public PassportTrackFragment() {
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


        View v = inflater.inflate(R.layout.passport_track_fragment, container, false);

        rv = (RecyclerView) v.findViewById(R.id.rv_passport_verify);
        // pd = (ProgressBar) v.findViewById(R.id.progressBar_citizen);
        search_track_bt = (Button) v.findViewById(R.id.passport_verify_bt);
        passport_verify_edt = (EditText) v.findViewById(R.id.passport_verify_edt);
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

        String TextInserted = passport_verify_edt.getText().toString();
        // 192.168.43.104
        final String url = "http://192.168.43.104:8092/PoliceApp/TrackPassport.aspx?DataFormat=ReceptNo&ReceptNo=" + TextInserted;

        final ProgressDialog pd = new ProgressDialog(getContext());
        pd.setMessage("Fetching Data ... ");
        pd.show();


        Ion.with(PassportTrackFragment.this)
                .load(url)
                .progressDialog(pd)
                .as(new TypeToken<List<Passport>>() {
                })
                .setCallback(new FutureCallback<List<Passport>>() {

                    @Override
                    public void onCompleted(Exception e, List<Passport> itemList) {
                        try {

                            if (itemList != null) {
                                final PassportTrackAdapter adapter = new PassportTrackAdapter(itemList, getContext());


                                rv.setAdapter(adapter);
                                rv.hasFixedSize();
                                rv.setLayoutManager(new LinearLayoutManager(getContext()));


                                pd.dismiss();
                            } else {
                                Toast.makeText(getContext(), "This Passport  is not yet out ", Toast.LENGTH_LONG).show();
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