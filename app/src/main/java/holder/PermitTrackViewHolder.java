package holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import company.override.huzykamz.policecopuganda.R;

/**
 * Created by Huzy_Kamz on 4/16/2017.
 */

public class PermitTrackViewHolder extends RecyclerView.ViewHolder {

    public View root;
    public TextView permit_status_txt;



    public PermitTrackViewHolder(final View itemView, final Context c) {
        super(itemView);
        root = itemView;
        permit_status_txt = (TextView) itemView.findViewById(R.id.permit_status_txt);





    }
}