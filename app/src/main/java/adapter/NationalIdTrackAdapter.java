package adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import company.override.huzykamz.policecopuganda.DisplayMissingItemActivity;
import company.override.huzykamz.policecopuganda.R;

import holder.MissingItemViewHolder;

import holder.NationalIdTrackViewHolder;
import holder.NationalIdViewHolder;
import model.MissingItem;
import model.NationalID_Model;

/**
 * Created by HUZY_KAMZ on 2/27/2017.
 */

public class NationalIdTrackAdapter extends RecyclerView.Adapter<NationalIdTrackViewHolder>{


    private List<NationalID_Model> item ;
    private Context context;
    private static  String Image_URl= "http://192.168.43.104:8092/PoliceAppWebPortal/COOPERP/NationalIdImages/";





    public NationalIdTrackAdapter(List<NationalID_Model> itemList, Context context) {
        this.item = itemList;
        this.context = context;
    }

    @Override
    public NationalIdTrackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_national_track,parent,false);
        NationalIdTrackViewHolder rcv = new NationalIdTrackViewHolder(layoutView,context);
        return rcv;

    }

    @Override
    public void onBindViewHolder(NationalIdTrackViewHolder holder, int position) {
        final  NationalID_Model sr = item.get(position);
        holder.nationalid_status_txt.setText(sr.getStatus());










    }

    @Override
    public int getItemCount() {
        return item.size();


    }
}
