package holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import company.override.huzykamz.policecopuganda.R;

/**
 * Created by Huzy_Kamz on 4/22/2017.
 */

public class VehicleViewHolder  extends RecyclerView.ViewHolder {

    public View root;
    public TextView vehicle_plate_no_txt;
    public TextView vehicle_names_txt;
    public TextView vehicle_chassis_no_txt;



    public VehicleViewHolder(final View itemView, final Context c) {
        super(itemView);
        root = itemView;
        vehicle_plate_no_txt = (TextView) itemView.findViewById(R.id.vehicle_plate_no_txt);
        vehicle_names_txt = (TextView) itemView.findViewById(R.id.vehicle_names_txt);
        vehicle_chassis_no_txt = (TextView) itemView.findViewById(R.id.vehicle_chassis_no_txt);
    }


}