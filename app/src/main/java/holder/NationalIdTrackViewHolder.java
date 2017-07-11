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

public class NationalIdTrackViewHolder  extends RecyclerView.ViewHolder {

    public View root;

    public TextView nationalid_status_txt ;



    public NationalIdTrackViewHolder(final View itemView, final Context c) {
        super(itemView);
        root = itemView;

        nationalid_status_txt = (TextView) itemView.findViewById(R.id.nationalid_status_txt);





    }
}
