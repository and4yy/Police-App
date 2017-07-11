package holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import company.override.huzykamz.policecopuganda.R;

/**
 * Created by HUZY_KAMZ on 11/23/2016.
 */

public class DetailsViewHolder  extends RecyclerView.ViewHolder {

    public View root;
    public TextView stationName;
    public ImageView stationPic;


    public DetailsViewHolder(final View itemView, final Context c) {
        super(itemView);
        root = itemView;
        stationName =(TextView) itemView.findViewById(R.id.station_txt);
       // stationPic = (ImageView) itemView.findViewById(R.id.station_image);



    }
}
