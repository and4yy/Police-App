package holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import company.override.huzykamz.policecopuganda.R;

/**
 * Created by Huzy_Kamz on 4/18/2017.
 */

public class PassportTrackViewHolder   extends RecyclerView.ViewHolder {

    public View root;
    public TextView passport_status_txt;



    public PassportTrackViewHolder(final View itemView, final Context c) {
        super(itemView);
        root = itemView;
        passport_status_txt = (TextView) itemView.findViewById(R.id.passport_status_txt);





    }
}