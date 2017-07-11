package holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import company.override.huzykamz.policecopuganda.R;

/**
 * Created by Huzy_Kamz on 3/31/2017.
 */

public class LocalLeaderViewHolder  extends RecyclerView.ViewHolder {

    public View root2;
    public TextView local_name;
    public ImageView img_local;


    public LocalLeaderViewHolder(final View itemView, final Context c) {
        super(itemView);
        root2 = itemView;
        img_local =(ImageView)itemView.findViewById(R.id.img_local);
        local_name = (TextView) itemView.findViewById(R.id.local_name_txt);


    }
}