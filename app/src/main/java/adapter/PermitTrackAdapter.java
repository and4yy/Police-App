package adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import company.override.huzykamz.policecopuganda.R;
import company.override.huzykamz.policecopuganda.SingleNewsActivity;
import holder.NewsViewHolder;
import holder.PermitTrackViewHolder;
import holder.PermitVerifyViewHolder;
import model.News;
import model.PermitVerifyModel;

/**
 * Created by Huzy_Kamz on 4/13/2017.
 */

public class PermitTrackAdapter extends RecyclerView.Adapter<PermitTrackViewHolder>{


    private List<PermitVerifyModel> item ;
    private Context context;

    private String Image_URl= "http://192.168.43.104:8092/PoliceAppWebPortal/COOPERP/PermitImages/";


    public PermitTrackAdapter(List<PermitVerifyModel> itemList, Context context) {
        this.item = itemList;
        this.context =  context;
    }

    @Override
    public PermitTrackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_permit_track,parent,false);
        PermitTrackViewHolder rcv = new PermitTrackViewHolder(layoutView,context);
        return rcv;

    }

    @Override
    public void onBindViewHolder(PermitTrackViewHolder holder, int position) {
        final  PermitVerifyModel sr = item.get(position);


        holder.permit_status_txt.setText(sr.getStatus());



    }

    @Override
    public int getItemCount() {
        return item.size();


    }
}

