package adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import company.override.huzykamz.policecopuganda.DetailsMissingCitizenActivity;
import company.override.huzykamz.policecopuganda.R;
import holder.MissingCitizenViewholder;

import model.MissingCitizen;


/**
 * Created by HUZY_KAMZ on 2/27/2017.
 */

public class MissingCitizenAdapter extends RecyclerView.Adapter<MissingCitizenViewholder>{


    private List<MissingCitizen> item ;
    private Context context;

    private String Image_URl= "http://192.168.43.104:8092/LostImages/";


    public MissingCitizenAdapter(List<MissingCitizen> itemList, Context context) {
        this.item = itemList;
        this.context = context;
    }

    @Override
    public MissingCitizenViewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_missing_citizen,parent,false);
        MissingCitizenViewholder rcv = new MissingCitizenViewholder(layoutView,context);
        return rcv;

    }

    @Override
    public void onBindViewHolder(MissingCitizenViewholder holder, int position) {
        final  MissingCitizen sr = item.get(position);

        holder.missing_name_txt.setText(sr.getName());
        holder.missing_address_txt.setText(sr.getAddress());
        holder.missing_age_txt.setText(sr.getAge());
        Picasso.with(MissingCitizenAdapter.this.context).load(Image_URl+sr.getImage()).into(holder.Image);
        final String Name = sr.getName();
        final String Age = sr.getAge();
        final String Address = sr.getAddress();
        final String Details = sr.getDetails();
        final String Image = sr.getImage();
        final String Call = sr.getPhone();


        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MissingCitizenAdapter.this.context, DetailsMissingCitizenActivity.class);
                i.putExtra("Name",Name);
                i.putExtra("Age",Age);
                i.putExtra("Address",Address);
                i.putExtra("Details",Details);
                i.putExtra("Image",Image);
                i.putExtra("Call",Call);

                context.startActivity(i);
            }
        });



    }

    @Override
    public int getItemCount() {
        return item.size();


    }
}
