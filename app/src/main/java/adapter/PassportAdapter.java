package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import company.override.huzykamz.policecopuganda.PassportActivity;
import company.override.huzykamz.policecopuganda.R;
import holder.PassportViewHolder;
import model.Passport;

/**
 * Created by Huzy_Kamz on 4/14/2017.
 */

public class PassportAdapter extends RecyclerView.Adapter<PassportViewHolder> {


    private List<Passport> item;
    private Context context;

    private String Image_URl = "http://192.168.43.104:8092/PoliceAppWebPortal/COOPERP/PassportImages/";


    public PassportAdapter(List<Passport> itemList, Context context) {
        this.item = itemList;
        this.context = context;
    }

    @Override
    public PassportViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_passport_verification, parent, false);
        PassportViewHolder rcv = new PassportViewHolder(layoutView, context);
        return rcv;

    }

    @Override
    public void onBindViewHolder(PassportViewHolder holder, int position) {
        final Passport sr = item.get(position);


        holder.surname.setText(sr.getSurname());
        holder.givenname.setText(sr.getGivenname());
        holder.type.setText(sr.getType());
        holder.nationality.setText(sr.getNationality());
        holder.sex.setText(sr.getSex());
        holder.dob.setText(sr.getDob());
        holder.placeofbirth.setText(sr.getPlaceofbirth());
        holder.dateofissue.setText(sr.getDateofissue());
        holder.dateofexpiry.setText(sr.getDateofexpiry());
//        holder.proffession.setText(sr.getProffession());
        holder.authority.setText(sr.getAuthority());

       Picasso.with(context).load(Image_URl+sr.getPhoto()).fit().into(holder.image_passport);


    }

    @Override
    public int getItemCount() {
        return item.size();


    }
}