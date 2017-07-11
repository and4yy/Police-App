package holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import company.override.huzykamz.policecopuganda.R;

/**
 * Created by Huzy_Kamz on 4/14/2017.
 */

public class PassportViewHolder extends RecyclerView.ViewHolder {

    public View root;
    public TextView surname;
    public TextView givenname;
    public TextView type;
    public TextView id_no;
    public TextView nationality;
    public TextView dob;
    public TextView sex;
    public TextView placeofbirth;
    public TextView dateofissue;
    public TextView dateofexpiry;
    public TextView proffession;
    public TextView authority;
    public ImageView image_passport;



    public PassportViewHolder(final View itemView, final Context c) {
        super(itemView);
        root = itemView;
        surname = (TextView) itemView.findViewById(R.id.passport_surname_txt);
        givenname = (TextView) itemView.findViewById(R.id.passport_given_name_txt);
        type = (TextView) itemView.findViewById(R.id.passport_type_txt);
        image_passport =(ImageView)itemView.findViewById(R.id.image_passport);
        nationality = (TextView) itemView.findViewById(R.id.passport_nationality_txt);
        dob = (TextView) itemView.findViewById(R.id.passport_dob_txt);
        sex = (TextView) itemView.findViewById(R.id.passport_sex_txt);
        placeofbirth = (TextView) itemView.findViewById(R.id.passport_placeofbirth_txt);
        dateofissue = (TextView) itemView.findViewById(R.id.passport_dateofissue_txt);
        dateofexpiry = (TextView) itemView.findViewById(R.id.passport_expiry_date_txt);
        authority = (TextView) itemView.findViewById(R.id.passport_authority_txt);
        proffession=(TextView)itemView.findViewById(R.id.passport_proffession_txt);




    }
}