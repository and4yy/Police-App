package company.override.huzykamz.policecopuganda;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Created by Huzy_Kamz on 4/7/2017.
 */

public class PoliceApp  extends Application{


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
