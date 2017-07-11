package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import company.override.huzykamz.policecopuganda.R;
import holder.MainHolder;
import model.ItemObjects;

/**
 * Created by HUZY_KAMZ on 12/20/2016.
 */

public class MainActivityAdapter extends RecyclerView.Adapter<MainHolder> {

    private List<ItemObjects> itemList;
    private Context context;

    public MainActivityAdapter(Context context, List<ItemObjects> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public MainHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, null);
        MainHolder rcv = new MainHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(MainHolder holder, int position) {
        holder.countryName.setText(itemList.get(position).getName());
       // holder.coun
        holder.countryPhoto.setImageResource(itemList.get(position).getPhoto());
      //  holder.countryPhoto.setImageResource(R.mipmap.ic_launcher);
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
