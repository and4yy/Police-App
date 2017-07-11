package company.override.huzykamz.policecopuganda;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.os.Bundle;
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

import adapter.MissingCitizenAdapter;
import adapter.NationalIdAdapter;
import model.MissingCitizen;
import model.NationalID_Model;

/**
 * Created by Huzy_Kamz on 4/11/2017.
 */

public class NationalIdVerificationFragment extends Fragment {


    RecyclerView rv_id_verification;
    EditText card_edt;
    Button search_bt;
    public NationalIdVerificationFragment() {
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


        View v = inflater.inflate(R.layout.idverificationfragment, container, false);
        rv_id_verification =(RecyclerView)v.findViewById(R.id.rv_id_verification);

        rv_id_verification.hasFixedSize();
        rv_id_verification.setLayoutManager(new LinearLayoutManager(getContext()));

        card_edt =(EditText)v.findViewById(R.id.card_edt);

        search_bt =(Button)v.findViewById(R.id.search_bt);

        search_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                OnSearch();
            }
        });




        return v;
    }






    void  OnSearch()
    {


        String TextInserted= card_edt.getText().toString();

      final  ProgressDialog pd = new ProgressDialog(getContext());
        pd.setMessage("Fetching Data ... ");
       // pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
       // pd.setIndeterminate(true);
        pd.show();

        // 192.168.43.104
        final String url = "http://192.168.43.104:8092/PoliceApp/NationalIDLoad.aspx?DataFormat=NationalID&NationalID="+TextInserted;

        Ion.with(getContext())
                .load(url)
                .progressDialog(pd)
                .as(new TypeToken<List<NationalID_Model>>() {
                })
                .setCallback(new FutureCallback<List<NationalID_Model>>() {

                    @Override
                    public void onCompleted(Exception e, List<NationalID_Model> itemList) {
                        try {

                            if(itemList!=null) {
                                final NationalIdAdapter adapter = new NationalIdAdapter(itemList, getContext());


                                rv_id_verification.setAdapter(adapter);
                                rv_id_verification.hasFixedSize();
                                rv_id_verification.setLayoutManager(new LinearLayoutManager(getContext()));


                                pd.dismiss();
                            }
                            else {
                                Toast.makeText(getContext(), "Your Card is not Found ", Toast.LENGTH_SHORT).show();
                                Toast.makeText(getContext(), "Make sure Your Connection is good ", Toast.LENGTH_SHORT).show();
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
