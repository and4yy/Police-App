package adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import company.override.huzykamz.policecopuganda.Diaspora;
import company.override.huzykamz.policecopuganda.ID_Verification;
import company.override.huzykamz.policecopuganda.LocalLeadersMenu;
import company.override.huzykamz.policecopuganda.Login;
import company.override.huzykamz.policecopuganda.PassportActivity;
import company.override.huzykamz.policecopuganda.R;
import company.override.huzykamz.policecopuganda.ReportAccident;
import company.override.huzykamz.policecopuganda.TrafficOffences;
import company.override.huzykamz.policecopuganda.TrafficSigns;
import company.override.huzykamz.policecopuganda.VehicleTabs;
import holder.MenuViewHolderTwo;
import model.MenuItems;


/**
 * Created by HUZY_KAMZ on 12/20/2016.
 */

public class MenuAdapterTwo extends RecyclerView.Adapter<MenuViewHolderTwo> {

    private List<MenuItems> itemList;
    private Context context;

    public MenuAdapterTwo(Context context, List<MenuItems> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public MenuViewHolderTwo onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item, null);
        MenuViewHolderTwo rcv = new MenuViewHolderTwo(layoutView,context);
        return rcv;
    }

    @Override
    public void onBindViewHolder(MenuViewHolderTwo holder, final int position) {
        final MenuItems sr = itemList.get(position);
        holder.txt_menu.setText(sr.getName());
        holder.img_menu.setImageResource(sr.getPhoto());

        switch (position){
            case 0:

                holder.root.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MenuAdapterTwo.this.context, ReportAccident.class);
                        context.startActivity(i);
                    }
                });
                break;
            case 1:
                holder.root.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent i = new Intent(MenuAdapterTwo.this.context, LocalLeadersMenu.class);
                        context.startActivity(i);
                    }
                });
                break;

            case 2:
                holder.root.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent i = new Intent(MenuAdapterTwo.this.context, TrafficOffences.class);
                        context.startActivity(i);
                    }
                });

                break;
            case 3:
                holder.root.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent i = new Intent(MenuAdapterTwo.this.context, ID_Verification.class);
                        context.startActivity(i);
                    }
                });

                break;
            case 4:
                holder.root.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent i = new Intent(MenuAdapterTwo.this.context, PassportActivity.class);
                        context.startActivity(i);
                    }
                });

                break;
            case 5:
                holder.root.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent i = new Intent(MenuAdapterTwo.this.context, TrafficSigns.class);
                        context.startActivity(i);
                    }
                });

                break;
            case 6:
                holder.root.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent i = new Intent(MenuAdapterTwo.this.context, Diaspora.class);
                        context.startActivity(i);
                    }
                });

                break;
            case 7:
                holder.root.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent i = new Intent(MenuAdapterTwo.this.context, VehicleTabs.class);
                        context.startActivity(i);
                    }
                });




        }


    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
