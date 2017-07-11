package adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import company.override.huzykamz.policecopuganda.ClearChargesActivity;
import company.override.huzykamz.policecopuganda.R;
import company.override.huzykamz.policecopuganda.SingleNewsActivity;
import company.override.huzykamz.policecopuganda.VehicleVerificationDetails;
import holder.NewsViewHolder;
import holder.PermitVerifyViewHolder;
import holder.VehicleChargesViewHolder;
import holder.VehicleViewHolder;
import model.News;
import model.PermitVerifyModel;
import model.VehicleChargesModel;
import model.VehicleRegistrationModel;

/**
 * Created by Huzy_Kamz on 4/13/2017.
 */

public class VehicleChargesAdapter extends RecyclerView.Adapter<VehicleChargesViewHolder>{


    private List<VehicleChargesModel> item ;
    private Context context;




    public VehicleChargesAdapter(List<VehicleChargesModel> itemList, Context context) {
        this.item = itemList;
        this.context =  context;
    }

    @Override
    public VehicleChargesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vehicle_charges,parent,false);
        VehicleChargesViewHolder rcv = new VehicleChargesViewHolder(layoutView,context);
        return rcv;

    }

    @Override
    public void onBindViewHolder(VehicleChargesViewHolder holder, int position) {
        final  VehicleChargesModel sr = item.get(position);


        holder.platenumber.setText(sr.getRegistrationnumber());
        holder.name_.setText(sr.getNames());
        holder.chassisno.setText(sr.getChassisnumber());
        holder.charge.setText(sr.getCharge());
        holder.crime.setText(sr.getOffence());
        holder.datecrime.setText(sr.getDateofcrime().replace(",","").replace(",",""));
        holder.permitnumber.setText(sr.getPermitnumber());
        holder.officerno.setText(sr.getOfficerno());
        holder.status.setText(sr.getClearingstatus());
        holder.item_receipt_charge_no_status.setText(sr.getTrafficchargereceiptno());

        final String Charge = sr.getCharge();


      final  String platenumber = sr.getRegistrationnumber();
      final  String name = sr.getNames();
        final String trafficreceiptno = sr.getTrafficchargereceiptno();
        final String  Tax = sr.getTax();

        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(VehicleChargesAdapter.this.context, ClearChargesActivity.class);
                i.putExtra("platenumber",platenumber);
                i.putExtra("name",name);
                i.putExtra("charge",Charge);
                i.putExtra("trafficreceiptno",trafficreceiptno);
                i.putExtra("tax",Tax);
                context.startActivity(i);
            }
        });




    }

    @Override
    public int getItemCount() {
        return item.size();


    }
}

