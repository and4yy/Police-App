package holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import company.override.huzykamz.policecopuganda.R;

/**
 * Created by HUZY_KAMZ on 11/8/2016.
 */

public class NewsViewHolder  extends RecyclerView.ViewHolder {

    public View root;
    public TextView headlines;
    public TextView details;
    public ImageView newsImage;


    public NewsViewHolder(final View itemView, final Context c) {
        super(itemView);
        root = itemView;
        newsImage =(ImageView)itemView.findViewById(R.id.news_image);
        headlines = (TextView) itemView.findViewById(R.id.headlines_txt);
        details=(TextView)itemView.findViewById(R.id.details_txt);


    }
}
