package model;

/**
 * Created by Huzy_Kamz on 3/30/2017.
 */

public class LocalLeaderModel {

    private String Name;
    private String Village;
    private String Post;
    private String phone;
    private String District;
    private String email;
    private String latitude;
    private String longitude;
    private String Photo;
    private String DistrictsId;

    public String getDistrictsId() {
        return DistrictsId;
    }

    public void setDistrictsId(String districtsId) {
        DistrictsId = districtsId;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String photo) {
        Photo = photo;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getVillage() {
        return Village;
    }

    public void setVillage(String village) {
        Village = village;
    }

    public String getPost() {
        return Post;
    }

    public void setPost(String post) {
        Post = post;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
