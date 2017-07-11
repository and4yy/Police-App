package holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import company.override.huzykamz.policecopuganda.R;

/**
 * Created by HUZY_KAMZ on 12/20/2016.
 */

public class MainHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView countryName;
    public ImageView countryPhoto;

    public MainHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        countryName = (TextView)itemView.findViewById(R.id.country_name);
        countryPhoto = (ImageView)itemView.findViewById(R.id.country_photo);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(), "Clicked Country Position = " + getPosition(), Toast.LENGTH_SHORT).show();
    }
}
