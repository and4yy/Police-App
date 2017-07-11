package model;

/**
 * Created by Huzy_Kamz on 4/13/2017.
 */

public class PermitVerifyModel {
private String id, name, id_no, Validity,
        dob, issuedby, permitnumber, issue_no, driver_restriction,
        gender, code, first_issue, vehicle_registration,photo,status;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId_no() {
        return id_no;
    }

    public void setId_no(String id_no) {
        this.id_no = id_no;
    }

    public String getValidity() {
        return Validity;
    }

    public void setValidity(String validity) {
        Validity = validity;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getIssuedby() {
        return issuedby;
    }

    public void setIssuedby(String issuedby) {
        this.issuedby = issuedby;
    }

    public String getPermitnumber() {
        return permitnumber;
    }

    public void setPermitnumber(String permitnumber) {
        this.permitnumber = permitnumber;
    }

    public String getIssue_no() {
        return issue_no;
    }

    public void setIssue_no(String issue_no) {
        this.issue_no = issue_no;
    }

    public String getDriver_restriction() {
        return driver_restriction;
    }

    public void setDriver_restriction(String driver_restriction) {
        this.driver_restriction = driver_restriction;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFirst_issue() {
        return first_issue;
    }

    public void setFirst_issue(String first_issue) {
        this.first_issue = first_issue;
    }

    public String getVehicle_registration() {
        return vehicle_registration;
    }

    public void setVehicle_registration(String vehicle_registration) {
        this.vehicle_registration = vehicle_registration;
    }
}
