package holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import company.override.huzykamz.policecopuganda.R;


/**
 * Created by HUZY_KAMZ on 2/9/2017.
 */

public class MenuViewHolder extends RecyclerView.ViewHolder {

    public View root;
    public TextView txt_menu;
    public ImageView img_menu;


    public MenuViewHolder(final View itemView, final Context c) {
        super(itemView);
        root = itemView;
        img_menu =(ImageView)itemView.findViewById(R.id.img_menu);
        txt_menu = (TextView) itemView.findViewById(R.id.txt_name_menu);


    }
}
