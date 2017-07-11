package holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import company.override.huzykamz.policecopuganda.R;

/**
 * Created by HUZY_KAMZ on 2/27/2017.
 */

public class MissingItemViewHolder extends RecyclerView.ViewHolder {

    public View root;
    public TextView item_name_txt;
    public TextView item_type_txt;
    public ImageView item_image;
    public TextView item_color_txt;

    public MissingItemViewHolder(final View itemView, final Context c) {
        super(itemView);
        root = itemView;
        item_image =(ImageView)itemView.findViewById(R.id.item_image);
        item_name_txt = (TextView) itemView.findViewById(R.id.item_name_txt);
        item_type_txt=(TextView)itemView.findViewById(R.id.item_type_txt);
        item_color_txt=(TextView)itemView.findViewById(R.id.item_color_txt);

    }
}
