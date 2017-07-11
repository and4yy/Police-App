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
import adapter.MissingItemAdapter;

import model.MissingItem;


/**
 * Created by HUZY_KAMZ on 2/11/2017.
 */

public class ItemMissingFragment extends Fragment {

    private Spinner categories;
    private Intent inn;
    private String EventId="";
    ProgressBar pd;
    private RecyclerView rv;
    String num="";
    public ItemMissingFragment() {
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


        View v = inflater.inflate(R.layout.fragment_missin_item, container, false);


        rv = (RecyclerView) v.findViewById(R.id.rv_missing_item);
        pd = (ProgressBar) v.findViewById(R.id.progressBar_missing_item);
        rv.hasFixedSize();
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        Load();


        return v;
    }









    public void Load(){

        // 192.168.43.104
        final String url = "http://192.168.43.104:8092/PoliceApp/Default.aspx?DataFormat=MissingItem";




        pd.setVisibility(View.VISIBLE);




        Ion.with(ItemMissingFragment.this)
                .load(url)
                .progressBar(pd)
                .as(new TypeToken<List<MissingItem>>() {
                })
                .setCallback(new FutureCallback<List<MissingItem>>() {

                    @Override
                    public void onCompleted(Exception e, List<MissingItem> itemList) {
                        try{
                        final MissingItemAdapter adapter = new MissingItemAdapter(itemList,getContext());


                            if(itemList!=null) {

                                rv.setAdapter(adapter);
                                rv.hasFixedSize();
                                rv.setLayoutManager(new LinearLayoutManager(getContext()));


                                pd.setVisibility(View.INVISIBLE);
                            }
                            else{

                                    Toast.makeText(getContext(), "Check your Internet Connectivity", Toast.LENGTH_SHORT).show();
                                    pd.setVisibility(View.INVISIBLE);
                            }
                        }
                        catch (Exception ex){
                            Toast.makeText(getContext(), "Check your Internet Connectivity", Toast.LENGTH_SHORT).show();
                            pd.setVisibility(View.INVISIBLE);
                        }
                    }
                });




    }




}
