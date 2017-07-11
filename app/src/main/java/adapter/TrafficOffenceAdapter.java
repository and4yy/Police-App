package adapter;

import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import java.util.List;


import company.override.huzykamz.policecopuganda.R;

import holder.TrafficOffenceViewHolder;

import model.TrafficOffenceModel;

/**
 * Created by Huzy_Kamz on 4/7/2017.
 */

public class TrafficOffenceAdapter extends RecyclerView.Adapter<TrafficOffenceViewHolder> {

    private List<TrafficOffenceModel> item ;
    private Context context;


    public TrafficOffenceAdapter(Context context, List<TrafficOffenceModel> itemList) {
        this.item = itemList;
        this.context = context;
    }

    @Override
    public TrafficOffenceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_traffic_offence,parent, false);
        TrafficOffenceViewHolder rcv = new TrafficOffenceViewHolder(layoutView,context);
        return rcv;
    }

    @Override
    public void onBindViewHolder(TrafficOffenceViewHolder holder, int position) {

        final TrafficOffenceModel sr = item.get(position);
        holder.offence.setText(sr.getOffence());
        holder.item_fine_max.setText(sr.getFine_max());
        holder.item_fine_min.setText(sr.getFine_min());
        holder.item_imprisonment_max.setText(sr.getImprisonment_max());
        holder.item_imprisonment_min.setText(sr.getImprisonment_min());



    }

    @Override
    public int getItemCount() {
        return this.item.size();
    }
}
