package company.override.huzykamz.policecopuganda;

import android.content.Context;

/**
 * Created by Huzy_Kamz on 4/4/2017.
 */

public class OnlineDataprovider {



    private String DistrictName ;
    private String DistrictsId;

    public OnlineDataprovider(String districtName, String districtsId) {
        DistrictName = districtName;
        DistrictsId = districtsId;
    }

    public String getDistrictName() {
        return DistrictName;
    }

    public void setDistrictName(String districtName) {
        DistrictName = districtName;
    }

    public String getDistrictsId() {
        return DistrictsId;
    }

    public void setDistrictsId(String districtsId) {
        DistrictsId = districtsId;
    }
}
