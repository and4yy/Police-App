package model;

/**
 * Created by HUZY_KAMZ on 1/29/2017.
 */

public class Profile {

    private String username;
    private String personal_contact;
    private String photo;
    private String emergency_contact_one;
    private String emergency_contact_two;
    private String gender;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPersonal_contact() {
        return personal_contact;
    }

    public void setPersonal_contact(String personal_contact) {
        this.personal_contact = personal_contact;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getEmergency_contact_one() {
        return emergency_contact_one;
    }

    public void setEmergency_contact_one(String emergency_contact_one) {
        this.emergency_contact_one = emergency_contact_one;
    }

    public String getEmergency_contact_two() {
        return emergency_contact_two;
    }

    public void setEmergency_contact_two(String emergency_contact_two) {
        this.emergency_contact_two = emergency_contact_two;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
