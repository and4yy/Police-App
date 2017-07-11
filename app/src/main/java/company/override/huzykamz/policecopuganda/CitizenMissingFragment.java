package company.override.huzykamz.policecopuganda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.List;

import adapter.MissingCitizenAdapter;
import model.MissingCitizen;


/**
 * Created by HUZY_KAMZ on 2/11/2017.
 */

public class CitizenMissingFragment extends Fragment {

    ProgressBar pd;
    private RecyclerView rv;

    public CitizenMissingFragment() {
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


        View v = inflater.inflate(R.layout.fragment_missing_citizen, container, false);

        rv = (RecyclerView) v.findViewById(R.id.rv_citizen);
        pd = (ProgressBar) v.findViewById(R.id.progressBar_citizen);
        rv.hasFixedSize();
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        LoadData();
        return v;
    }







    public void LoadData(){


        // 192.168.43.104
         final String url = "http://192.168.43.104:8092/PoliceApp/Default.aspx?DataFormat=MissingCitizen";




        pd.setVisibility(View.VISIBLE);




        Ion.with(CitizenMissingFragment.this)
                .load(url)
                .progressBar(pd)
                .as(new TypeToken<List<MissingCitizen>>() {
                })
                .setCallback(new FutureCallback<List<MissingCitizen>>() {

                    @Override
                    public void onCompleted(Exception e, List<MissingCitizen> itemList) {
                        try {

                            if(itemList!=null) {
                                final MissingCitizenAdapter adapter = new MissingCitizenAdapter(itemList, getContext());


                                rv.setAdapter(adapter);
                                rv.hasFixedSize();
                                rv.setLayoutManager(new LinearLayoutManager(getContext()));


                                pd.setVisibility(View.INVISIBLE);
                            }
                            else {
                                Toast.makeText(getContext(), "No Internet Connection!", Toast.LENGTH_SHORT).show();
                                pd.setVisibility(View.INVISIBLE);
                            }
                        }
                        catch (Exception ex){
                            Toast.makeText(getContext(), "No Internet Connection!", Toast.LENGTH_SHORT).show();
                            pd.setVisibility(View.INVISIBLE);
                        }
                    }
                });




    }




}
