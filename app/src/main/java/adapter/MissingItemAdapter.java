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

import model.MissingItem;

/**
 * Created by HUZY_KAMZ on 2/27/2017.
 */

public class MissingItemAdapter extends RecyclerView.Adapter<MissingItemViewHolder>{


    private List<MissingItem> item ;
    private Context context;

    private String Image_URl= "http://192.168.43.104:8092/LostImages/";


    public MissingItemAdapter(List<MissingItem> itemList, Context context) {
        this.item = itemList;
        this.context = context;
    }

    @Override
    public MissingItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_missing_item,parent,false);
        MissingItemViewHolder rcv = new MissingItemViewHolder(layoutView,context);
        return rcv;

    }

    @Override
    public void onBindViewHolder(MissingItemViewHolder holder, int position) {
        final  MissingItem sr = item.get(position);
        holder.item_color_txt.setText(sr.getColor());
        holder.item_name_txt.setText(sr.getName());
        holder.item_type_txt.setText(sr.getType());

        final String Name = sr.getName();
        final String Type = sr.getType();
        final  String Color = sr.getColor();
        final String Details = sr.getDetails();
        final String Image = sr.getImage();


        Picasso.with(context).load(Image_URl+sr.getImage()).fit().into(holder.item_image);
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MissingItemAdapter.this.context, DisplayMissingItemActivity.class);
                i.putExtra("Name",Name);
                i.putExtra("Type",Type);
                i.putExtra("Color",Color);
                i.putExtra("Details",Details);
                i.putExtra("Image",Image);
                context.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return item.size();


    }
}
