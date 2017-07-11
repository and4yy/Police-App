package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import company.override.huzykamz.policecopuganda.R;
import holder.PassportTrackViewHolder;
import holder.PermitTrackViewHolder;
import model.Passport;
import model.PermitVerifyModel;

/**
 * Created by Huzy_Kamz on 4/18/2017.
 */

public class PassportTrackAdapter   extends RecyclerView.Adapter<PassportTrackViewHolder>{


    private List<Passport> item ;
    private Context context;




    public PassportTrackAdapter(List<Passport> itemList, Context context) {
        this.item = itemList;
        this.context =  context;
    }

    @Override
    public PassportTrackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_passport_track,parent,false);


        PassportTrackViewHolder rcv = new PassportTrackViewHolder(layoutView,context);

        return rcv;

    }

    @Override
    public void onBindViewHolder(PassportTrackViewHolder holder, int position) {
        final  Passport sr = item.get(position);


        holder.passport_status_txt.setText(sr.getStatus());



    }

    @Override
    public int getItemCount() {
        return item.size();


    }
}

