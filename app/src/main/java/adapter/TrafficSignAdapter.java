package adapter;

import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;
import company.override.huzykamz.policecopuganda.R;

import holder.TrafficSignViewHolder;

import model.TrafficSignModel;

/**
 * Created by Huzy_Kamz on 4/7/2017.
 */

public class TrafficSignAdapter extends RecyclerView.Adapter<TrafficSignViewHolder> {

    private List<TrafficSignModel> item ;
    private Context context;
    private String Image_URl= "http://192.168.43.104:8092/PoliceAppWebPortal/COOPERP/TrafficSigns/";

    public TrafficSignAdapter(Context context, List<TrafficSignModel> itemList) {
        this.item = itemList;
        this.context = context;
    }

    @Override
    public TrafficSignViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_traffic_signs,parent, false);
        TrafficSignViewHolder rcv = new TrafficSignViewHolder(layoutView,context);
        return rcv;
    }

    @Override
    public void onBindViewHolder(TrafficSignViewHolder holder, int position) {

        final TrafficSignModel sr = item.get(position);
        holder.signname_txt.setText(sr.getSignname());
        Picasso.with(TrafficSignAdapter.this.context).load(Image_URl+sr.getPhoto()).fit().into(holder.image_traffic_signs);





    }

    @Override
    public int getItemCount() {
        return this.item.size();
    }
}
