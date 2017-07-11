package holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import company.override.huzykamz.policecopuganda.R;

/**
 * Created by Huzy_Kamz on 4/7/2017.
 */

public class TrafficSignViewHolder  extends RecyclerView.ViewHolder {

    public View root;

    public TextView signname_txt;
    public ImageView image_traffic_signs;




    public TrafficSignViewHolder(final View itemView, final Context c) {
        super(itemView);
        root = itemView;
        image_traffic_signs = (ImageView) itemView.findViewById(R.id.image_traffic_signs);
        signname_txt = (TextView) itemView.findViewById(R.id.signname_txt);
    }


}
