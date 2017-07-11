package adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import company.override.huzykamz.policecopuganda.R;
import company.override.huzykamz.policecopuganda.SinglePoliceDetailsActivity;
import holder.DetailsViewHolder;
import holder.NewsViewHolder;
import model.Details;
import model.News;

/**
 * Created by HUZY_KAMZ on 11/23/2016.
 */

public class PoliceDetailsAdapter extends  RecyclerView.Adapter<DetailsViewHolder> {

    private List<Details> item ;
    private Context context;
   private String Image_Url="http://192.168.43.104:7777/PoliceApp/PoliceDetailsPhotos/";


    public PoliceDetailsAdapter(List<Details> item, Context context) {
        this.item = item;
        this.context = context;
    }

    @Override
    public DetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.details_items,parent,false);
        DetailsViewHolder rcv = new DetailsViewHolder(layoutView,context);
        return rcv;

    }

    @Override
    public void onBindViewHolder(DetailsViewHolder holder, int position) {
       final Details sr = item.get(position);
        holder.stationName.setText(sr.getStationName());

        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(PoliceDetailsAdapter.this.context, SinglePoliceDetailsActivity.class);
                i.putExtra("Police_Name",sr.getStationName());
                i.putExtra("Oc_Name",sr.getOc_Name());
                i.putExtra("Contact_One",sr.getContactOne());
                i.putExtra("Contact_Two",sr.getContactTwo());
                i.putExtra("Police_Details",sr.getPoliceDetails());
                i.putExtra("Latitude",sr.getLatitude());
                i.putExtra("Longitude",sr.getLongitude());
                i.putExtra("Oc_Pic",sr.getOc_Photo());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
    //    System.out.println("Size : "+item.size());
        return item.size();

       // System.out.println(item.size());
    }
}
