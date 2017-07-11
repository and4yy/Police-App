package holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import company.override.huzykamz.policecopuganda.R;

/**
 * Created by Huzy_Kamz on 4/11/2017.
 */

public class NationalIdViewHolder extends RecyclerView.ViewHolder {

    public View root;
    public TextView country_id_txt;
    public TextView given_name_txt ;
    public TextView sex_id_txt;
    public TextView card_no_txt;
    public TextView nin_txt ;
    public TextView dob_id_txt;
    public TextView card_expiry_date_txt;
    public TextView surname_txt;
    public TextView nationality_txt;
    public ImageView image_national_id;


    public NationalIdViewHolder(final View itemView, final Context c) {
        super(itemView);
        root = itemView;
        image_national_id =(ImageView)itemView.findViewById(R.id.image_national_id);
        given_name_txt = (TextView) itemView.findViewById(R.id.given_name_txt);

        sex_id_txt =(TextView)itemView.findViewById(R.id.sex_id_txt);
        card_no_txt =(TextView)itemView.findViewById(R.id.card_no_txt);
        nin_txt =(TextView)itemView.findViewById(R.id.nin_txt);
        dob_id_txt =(TextView) itemView.findViewById(R.id.dob_id_txt);
        card_expiry_date_txt =(TextView)itemView.findViewById(R.id.card_expiry_date_txt);
        surname_txt =(TextView)itemView.findViewById(R.id.surname_txt);
        nationality_txt =(TextView)itemView.findViewById(R.id.nationality_txt);




    }
}
