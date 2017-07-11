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

public class TrafficOffenceViewHolder   extends RecyclerView.ViewHolder {

    public View root;
    public TextView offence;
    public TextView item_fine_max;
    public TextView item_fine_min;
    public TextView item_imprisonment_max;
    public TextView item_imprisonment_min;



    public TrafficOffenceViewHolder(final View itemView, final Context c) {
        super(itemView);
        root = itemView;
        item_fine_max = (TextView) itemView.findViewById(R.id.item_fine_max);
        offence = (TextView) itemView.findViewById(R.id.item_offence);
        item_fine_min = (TextView) itemView.findViewById(R.id.item_fine_min);
        item_imprisonment_max = (TextView) itemView.findViewById(R.id.item_imprisonment_max);
        item_imprisonment_min = (TextView) itemView.findViewById(R.id.item_imprisonment_min);

    }


    }