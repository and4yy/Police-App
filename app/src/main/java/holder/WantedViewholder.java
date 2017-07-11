package holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import company.override.huzykamz.policecopuganda.R;

/**
 * Created by Huzy_Kamz on 3/23/2017.
 */

public class WantedViewholder extends RecyclerView.ViewHolder {

    public View root;
    public TextView name_wanted;
    public TextView information_wanted;
    public ImageView image_wanted;


    public WantedViewholder(final View itemView, final Context c) {
        super(itemView);
        root = itemView;
        image_wanted =(ImageView)itemView.findViewById(R.id.image_wanted);
        information_wanted = (TextView) itemView.findViewById(R.id.information_wanted);
        name_wanted=(TextView)itemView.findViewById(R.id.name_wanted_list);


    }
}