package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import company.override.huzykamz.policecopuganda.R;
import holder.MainHolder;
import holder.WantedViewholder;
import model.ItemObjects;
import model.News;
import model.Wanted;

/**
 * Created by Huzy_Kamz on 3/23/2017.
 */

public class WantedAdapter extends RecyclerView.Adapter<WantedViewholder> {

    private List<Wanted> itemList;
    private Context context;
    private String Image_URl= "http://192.168.43.104:8092/COOPERP/WantedImages/";
    public WantedAdapter(Context context, List<Wanted> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public WantedViewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wanted_list, parent,false);
        WantedViewholder rcv = new WantedViewholder(layoutView,context);
        return rcv;
    }

    @Override
    public void onBindViewHolder(WantedViewholder holder, int position) {

        final Wanted sr = itemList.get(position);
        holder.name_wanted.setText(sr.getName());
        holder.information_wanted.setText(sr.getInformation());
        Picasso.with(context).load(Image_URl+sr.getImage()).fit().into(holder.image_wanted);
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
