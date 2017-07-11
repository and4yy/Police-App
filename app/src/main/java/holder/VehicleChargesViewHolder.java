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

public class VehicleChargesViewHolder  extends RecyclerView.ViewHolder {

    public View root;
    public TextView platenumber;
    public TextView name_;
    public TextView chassisno;
    public TextView charge;
    public TextView crime;
    public TextView datecrime;
    public TextView permitnumber;
    public TextView officerno;
    public TextView status;
    public TextView item_receipt_charge_no_status;


    public VehicleChargesViewHolder(final View itemView, final Context c) {
        super(itemView);
        root = itemView;
        name_ = (TextView) itemView.findViewById(R.id.item_charges_names);
        chassisno = (TextView) itemView.findViewById(R.id.item_charges_chassisno);
        charge = (TextView) itemView.findViewById(R.id.item_charges_charge);
        crime = (TextView) itemView.findViewById(R.id.item_charge_crime_);
        datecrime = (TextView) itemView.findViewById(R.id.item_charge_datecrime_status);
        permitnumber = (TextView) itemView.findViewById(R.id.item_charge_permitnumber_status);
        officerno = (TextView) itemView.findViewById(R.id.item_charge_officeno_status);
        status = (TextView) itemView.findViewById(R.id.item_charges_status);
        platenumber =(TextView)itemView.findViewById(R.id.item_charges_plateno);
        item_receipt_charge_no_status =(TextView)itemView.findViewById(R.id.item_receipt_charge_no_status);
    }


}