package holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import company.override.huzykamz.policecopuganda.R;

/**
 * Created by Huzy_Kamz on 4/13/2017.
 */

public class PermitVerifyViewHolder extends RecyclerView.ViewHolder {

    public View root;
    public TextView name;
    public TextView issuedby;
    public TextView gender;
    public TextView id_no;
    public TextView valididty;
    public TextView dob;
    public TextView driverrestriction;
    public TextView code;
    public TextView firstissue;
    public TextView vehiclerestriction;
    public TextView issue_no;
    public ImageView image_permit;


    public PermitVerifyViewHolder(final View itemView, final Context c) {
        super(itemView);
        root = itemView;
        name = (TextView) itemView.findViewById(R.id.permit_verify_name);
        image_permit =(ImageView)itemView.findViewById(R.id.image_permit);
        issuedby = (TextView) itemView.findViewById(R.id.permitg_verify_issued_by);
        gender = (TextView) itemView.findViewById(R.id.permit_verify_gender);
        id_no = (TextView) itemView.findViewById(R.id.permit_verify_id_no);
        valididty = (TextView) itemView.findViewById(R.id.permit_verify_validity);
        dob = (TextView) itemView.findViewById(R.id.permit_verify_dob);
        driverrestriction=(TextView) itemView.findViewById(R.id.permit_verify_driver_restriction);
        code =(TextView) itemView.findViewById(R.id.permit_verify_code);
        firstissue =(TextView) itemView.findViewById(R.id.permit_verify_first_issue);
        vehiclerestriction =(TextView) itemView.findViewById(R.id.permit_verify_vehicle_restriction);
        issue_no = (TextView) itemView.findViewById(R.id.permit_verify_issue_no);




    }
}