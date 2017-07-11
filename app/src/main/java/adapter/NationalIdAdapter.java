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

import holder.NationalIdViewHolder;
import model.MissingItem;
import model.NationalID_Model;

/**
 * Created by HUZY_KAMZ on 2/27/2017.
 */

public class NationalIdAdapter extends RecyclerView.Adapter<NationalIdViewHolder>{


    private List<NationalID_Model> item ;
    private Context context;
    private static  String Image_URl= "http://192.168.43.104:8092/PoliceAppWebPortal/COOPERP/NationalIdImages/";





    public NationalIdAdapter(List<NationalID_Model> itemList, Context context) {
        this.item = itemList;
        this.context = context;
    }

    @Override
    public NationalIdViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_id_verifivation,parent,false);
        NationalIdViewHolder rcv = new NationalIdViewHolder(layoutView,context);
        return rcv;

    }

    @Override
    public void onBindViewHolder(NationalIdViewHolder holder, int position) {
        final  NationalID_Model sr = item.get(position);
        holder.card_expiry_date_txt.setText(sr.getDoe());
        holder.surname_txt.setText(sr.getSurname());
        holder.given_name_txt.setText(sr.getGivenname());
        holder.nationality_txt.setText(sr.getNationality());
        holder.nationality_txt.setText(sr.getNationality());
        holder.sex_id_txt.setText(sr.getSex());
        holder.dob_id_txt.setText(sr.getDob());
        holder.card_no_txt.setText(sr.getCard_no());
        holder.nin_txt.setText(sr.getNin());
        holder.card_expiry_date_txt.setText(sr.getDoe());


        Picasso.with(context).load(Image_URl+sr.getPhoto()).fit().into(holder.image_national_id);






    }

    @Override
    public int getItemCount() {
        return item.size();


    }
}
