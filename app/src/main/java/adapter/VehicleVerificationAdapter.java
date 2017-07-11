package adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import company.override.huzykamz.policecopuganda.R;
import company.override.huzykamz.policecopuganda.SingleNewsActivity;
import company.override.huzykamz.policecopuganda.VehicleVerificationDetails;
import holder.NewsViewHolder;
import holder.PermitVerifyViewHolder;
import holder.VehicleViewHolder;
import model.News;
import model.PermitVerifyModel;
import model.VehicleRegistrationModel;

/**
 * Created by Huzy_Kamz on 4/13/2017.
 */

public class VehicleVerificationAdapter extends RecyclerView.Adapter<VehicleViewHolder>{


    private List<VehicleRegistrationModel> item ;
    private Context context;




    public VehicleVerificationAdapter(List<VehicleRegistrationModel> itemList, Context context) {
        this.item = itemList;
        this.context =  context;
    }

    @Override
    public VehicleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vehicle_verification,parent,false);
        VehicleViewHolder rcv = new VehicleViewHolder(layoutView,context);
        return rcv;

    }

    @Override
    public void onBindViewHolder(VehicleViewHolder holder, int position) {
        final  VehicleRegistrationModel sr = item.get(position);


        holder.vehicle_chassis_no_txt.setText(sr.getChassisnumber());
        holder.vehicle_names_txt.setText(sr.getNames());
        holder.vehicle_plate_no_txt.setText(sr.getRegistrationnumber());
        final String Name = sr.getNames();
        final String ChassiNo = sr.getChassisnumber();
        final String RegistrationNumber = sr.getRegistrationnumber();
        final String DateOFRegistration = sr.getDateofregistration();
        final String OTV = sr.getOtv_number();
        final String PlaceOfRegistration = sr.getPlaceofregistration();
        final String Make = sr.getMake();
        final String ManufactureModel = sr.getManufucture_model();
        final String CountryOfOrigin = sr.getCountryoforigin();
        final String FeesClassification = sr.getFeesclassification();
        final String BodyDescription = sr.getBodydescription();
        final String Color = sr.getColour();
        final String YearOfManufucture = sr.getYearofmanufacture();
        final String ClassificationOfVehicle = sr.getClassificationofvehicle();
        final String Fuel = sr.getFuel();
        final String Power = sr.getPower();
        final String EngineNumber = sr.getEnginenumber();
        final String ChassisNumber = sr.getChassisnumber();
        final String NetWeihgt = sr.getNetweight();
        final String GrossWeight = sr.getGrossweight();
        final String WeightUnits = sr.getWeightunits();
        final String SizeOfTyres = sr.getSizeoftyres();
        final String NumberOfWheels = sr.getNumberofwheels();
        final String NumberOfAxles = sr.getNumberofaxles();
        final String SeatingCapacity = sr.getSeatingcapacity();
        final String Purpose = sr.getPurpose();
        final String CategoryOfOwnership = sr.getCategoryofownership();
        final String TaxCategory = sr.getTaxcategory();
        final String Attachments = sr.getAttachments();
        final String Endorsements = sr.getEndorsements();
        final String IssueDate = sr.getIssuedate();
        final String Remarks = sr.getRemarks();
        final String Tin = sr.getTin();
        final String District = sr.getDistrict();
        final String Country = sr.getCounty();
        final String PostalAddress = sr.getPostaladdress();
        final String NameOfSignatory = sr.getNameofsignatory();

        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(VehicleVerificationAdapter.this.context, VehicleVerificationDetails.class);
                i.putExtra("Name",Name);
                i.putExtra("RegistrationNumber",RegistrationNumber);
                i.putExtra("DateOFRegistration",DateOFRegistration);
                i.putExtra("OTV",OTV);
                i.putExtra("PlaceOfRegistration",PlaceOfRegistration);
                i.putExtra("Make",Make);
                i.putExtra("ManufactureModel",ManufactureModel);
                i.putExtra("CountryOfOrigin",CountryOfOrigin);
                i.putExtra("FeesClassification",FeesClassification);
                i.putExtra("BodyDescription",BodyDescription);
                i.putExtra("Color",Color);
                i.putExtra("YearOfManufucture",YearOfManufucture);
                i.putExtra("ClassificationOfVehicle",ClassificationOfVehicle);
                i.putExtra("Fuel",Fuel);
                i.putExtra("Power",Power);
                i.putExtra("EngineNumber",EngineNumber);
                i.putExtra("ChassisNumber",ChassisNumber);
                i.putExtra("NetWeihgt",NetWeihgt);
                i.putExtra("GrossWeight",GrossWeight);
                i.putExtra("WeightUnits",WeightUnits);
                i.putExtra("SizeOfTyres",SizeOfTyres);
                i.putExtra("NumberOfWheels",NumberOfWheels);
                i.putExtra("NumberOfAxles",NumberOfAxles);
                i.putExtra("SeatingCapacity",SeatingCapacity);
                i.putExtra("Purpose",Purpose);
                i.putExtra("CategoryOfOwnership",CategoryOfOwnership);
                i.putExtra("TaxCategory",TaxCategory);
                i.putExtra("Attachments",Attachments);
                i.putExtra("Endorsements",Endorsements);
                i.putExtra("IssueDate",IssueDate);
                i.putExtra("Remarks",Remarks);
                i.putExtra("Tin",Tin);
                i.putExtra("District",District);
                i.putExtra("Country",Country);
                i.putExtra("PostalAddress",PostalAddress);
                i.putExtra("NameOfSignatory",NameOfSignatory);
                context.startActivity(i);
            }
        });




    }

    @Override
    public int getItemCount() {
        return item.size();


    }
}

