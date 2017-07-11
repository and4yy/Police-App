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

public class MissingCitizenViewholder  extends RecyclerView.ViewHolder {

    public View root;
    public TextView missing_name_txt;
    public TextView missing_age_txt;
    public TextView missing_address_txt;
    public ImageView Image;


    public MissingCitizenViewholder(final View itemView, final Context c) {
        super(itemView);
        root = itemView;
        Image =(ImageView)itemView.findViewById(R.id.missing_citizen_image);
        missing_name_txt = (TextView) itemView.findViewById(R.id.missing_name_txt);
        missing_age_txt=(TextView)itemView.findViewById(R.id.missing_age_txt);
        missing_address_txt =(TextView)itemView.findViewById(R.id.missing_address_txt);


    }
}
