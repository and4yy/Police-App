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
import company.override.huzykamz.policecopuganda.SinglePoliceDetailsActivity;
import holder.NewsViewHolder;
import model.News;


/**
 * Created by HUZY_KAMZ on 10/12/2016.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsViewHolder>{


    private List<News> item ;
    private Context context;

 private String Image_URl= "http://192.168.43.104:8092/PoliceApp/NewsPhotos/";


    public NewsAdapter(List<News> itemList, Context context) {
        this.item = itemList;
        this.context = context;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news,parent,false);
        NewsViewHolder rcv = new NewsViewHolder(layoutView,context);
        return rcv;

    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
      final  News sr = item.get(position);

        holder.headlines.setText(sr.getHeadlines());
        holder.details.setText(sr.getDetails());

        Picasso.with(context).load(Image_URl+sr.getNewsPhoto()).fit().into(holder.newsImage);

        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(NewsAdapter.this.context, SingleNewsActivity.class);
                i.putExtra("Headlines",sr.getHeadlines());
                i.putExtra("Details",sr.getDetails());
                i.putExtra("NewsImage",sr.getNewsPhoto());

                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return item.size();


    }
}
