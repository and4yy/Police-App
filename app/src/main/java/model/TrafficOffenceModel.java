package model;

/**
 * Created by Huzy_Kamz on 4/7/2017.
 */

public class TrafficOffenceModel {
    private String id;
    private String Offence;
    private String Fine_min;
    private String Fine_max;
    private String Imprisonment_min;
    private String Imprisonment_max;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOffence() {
        return Offence;
    }

    public void setOffence(String offence) {
        Offence = offence;
    }

    public String getFine_min() {
        return Fine_min;
    }

    public void setFine_min(String fine_min) {
        this.Fine_min = fine_min;
    }

    public String getFine_max() {
        return Fine_max;
    }

    public void setFine_max(String fine_max) {
        this.Fine_max = fine_max;
    }

    public String getImprisonment_min() {
        return Imprisonment_min;
    }

    public void setImprisonment_min(String imprisonment_min) {
        Imprisonment_min = imprisonment_min;
    }

    public String getImprisonment_max() {
        return Imprisonment_max;
    }

    public void setImprisonment_max(String imprisonment_max) {
        Imprisonment_max = imprisonment_max;
    }
}
