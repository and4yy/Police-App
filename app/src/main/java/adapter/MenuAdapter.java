package adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import company.override.huzykamz.policecopuganda.About;
import company.override.huzykamz.policecopuganda.CrimesReporting;
import company.override.huzykamz.policecopuganda.Login;
import company.override.huzykamz.policecopuganda.MissingTabs;
import company.override.huzykamz.policecopuganda.PoliceDetails;
import company.override.huzykamz.policecopuganda.R;
import company.override.huzykamz.policecopuganda.ShakeActivity;
import company.override.huzykamz.policecopuganda.WantedList;
import holder.MenuViewHolder;
import model.MenuItems;


/**
 * Created by HUZY_KAMZ on 12/20/2016.
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuViewHolder> {

    private List<MenuItems> itemList;
    private Context context;
    private static final String IS_LOGIN = "IsLoggedIn";
    SharedPreferences pref ;
    SharedPreferences sharedpreferences;
    public MenuAdapter(Context context, List<MenuItems> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item, null);
        MenuViewHolder rcv = new MenuViewHolder(layoutView,context);
        return rcv;
    }

    @Override
    public void onBindViewHolder(MenuViewHolder holder, final int position) {
        final MenuItems sr = itemList.get(position);
        holder.txt_menu.setText(sr.getName());
       holder.img_menu.setImageResource(sr.getPhoto());

        switch (position){
            case 0:

                holder.root.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent i = new Intent(MenuAdapter.this.context, MissingTabs.class);
                        context.startActivity(i);
                    }
                });
                break;
            case 1:

                if(!isNotLoggedIn()) {
                    holder.root.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent i = new Intent(MenuAdapter.this.context, Login.class);
                            context.startActivity(i);

                        }
                    }); 
                }
                else {
                    holder.root.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent i = new Intent(MenuAdapter.this.context, ShakeActivity.class);
                            context.startActivity(i);

                        }
                    });
                }
                break;
            case 2:
                holder.root.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {



                        Intent i = new Intent(MenuAdapter.this.context, PoliceDetails.class);
                        context.startActivity(i);
                    }
                });
                break ;

            case 3:
                holder.root.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent i = new Intent(MenuAdapter.this.context, CrimesReporting.class);
                        context.startActivity(i);
                    }
                });

                break;
            case 4:
                holder.root.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent i = new Intent(MenuAdapter.this.context, WantedList.class);
                        context.startActivity(i);
                    }
                });
                break ;

            case 5:
                holder.root.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent i = new Intent(MenuAdapter.this.context, About.class);
                        context.startActivity(i);
                    }
                });
                break ;
        }


    }


    public boolean isNotLoggedIn(){

       pref =MenuAdapter.this.context.getSharedPreferences("MyPref", 0);
        return pref.getBoolean(IS_LOGIN, false);
    }


    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
