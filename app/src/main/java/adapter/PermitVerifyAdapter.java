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
import holder.PermitVerifyViewHolder;
import model.News;
import model.PermitVerifyModel;

/**
 * Created by Huzy_Kamz on 4/13/2017.
 */

public class PermitVerifyAdapter extends RecyclerView.Adapter<PermitVerifyViewHolder>{


    private List<PermitVerifyModel> item ;
    private Context context;

    private String Image_URl= "http://192.168.43.104:8092/PoliceAppWebPortal/COOPERP/PermitImages/";


    public PermitVerifyAdapter(List<PermitVerifyModel> itemList, Context context) {
        this.item = itemList;
        this.context =  context;
    }

    @Override
    public PermitVerifyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_permit_verification,parent,false);
        PermitVerifyViewHolder rcv = new PermitVerifyViewHolder(layoutView,context);
        return rcv;

    }

    @Override
    public void onBindViewHolder(PermitVerifyViewHolder holder, int position) {
        final  PermitVerifyModel sr = item.get(position);


          holder.name.setText(sr.getName());
         holder.issuedby.setText(sr.getIssuedby());
        holder.gender.setText(sr.getGender());
        holder.id_no.setText(sr.getId_no());
        holder.valididty.setText(sr.getValidity());
        holder.dob.setText(sr.getDob());
        holder.driverrestriction.setText(sr.getDriver_restriction());
        holder.code.setText(sr.getCode());
        holder.firstissue.setText(sr.getFirst_issue());
        holder.vehiclerestriction.setText(sr.getVehicle_registration());
        holder.issue_no.setText(sr.getIssue_no());

        Picasso.with(context).load(Image_URl+sr.getPhoto()).fit().into(holder.image_permit);


    }

    @Override
    public int getItemCount() {
        return item.size();


    }
}

