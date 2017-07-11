package adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import company.override.huzykamz.policecopuganda.DetailsLocalLeader;
import company.override.huzykamz.policecopuganda.R;
import holder.LocalLeaderViewHolder;
import model.LocalLeaderModel;


/**
 * Created by Huzy_Kamz on 3/31/2017.
 */

public class LocalLeadersAdapter   extends RecyclerView.Adapter<LocalLeaderViewHolder> {

    private List<LocalLeaderModel> item ;
    private Context context;
    private String Image_URl= "http://192.168.43.104:8092/COOPERP/LocalLeaders/";

    public LocalLeadersAdapter(Context context, List<LocalLeaderModel> itemList) {
        this.item = itemList;
        this.context = context;
    }

    @Override
    public LocalLeaderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_local_leader,parent, false);
        LocalLeaderViewHolder rcv = new LocalLeaderViewHolder(layoutView,context);
        return rcv;
    }

    @Override
    public void onBindViewHolder(LocalLeaderViewHolder holder, int position) {

        final LocalLeaderModel sr = item.get(position);
        holder.local_name.setText(sr.getName());
        Picasso.with(LocalLeadersAdapter.this.context).load(Image_URl+sr.getPhoto()).into(holder.img_local);

      final  String Phone = sr.getPhone();
        final  String Email = sr.getEmail();
        final String Name = sr.getName();
        final String Image = sr.getPhoto();

        holder.root2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LocalLeadersAdapter.this.context,DetailsLocalLeader.class);
                i.putExtra("Call",Phone);
                i.putExtra("Email",Email);
                i.putExtra("Name",Name);
                i.putExtra("Image",Image);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);


            }
        });


    }

    @Override
    public int getItemCount() {
        return this.item.size();
    }
}
