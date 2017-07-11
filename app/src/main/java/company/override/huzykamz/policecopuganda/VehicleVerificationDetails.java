package company.override.huzykamz.policecopuganda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

public class VehicleVerificationDetails extends AppCompatActivity {


    private TextView regnumb,dor, otv,
            por, make, mm, coo
            ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_verification_details);

     getSupportActionBar().setDisplayShowHomeEnabled(true);
     android.support.v7.app.ActionBar actionBar = getSupportActionBar();
     actionBar.setDisplayHomeAsUpEnabled(true);
     getSupportActionBar().setTitle("LOG BOOK ");

         Intent  i = getIntent();

     if(i!= null) {

      regnumb = (TextView) findViewById(R.id.details_registration_number);
      String RegNum = i.getStringExtra("RegistrationNumber");
      regnumb.setText(RegNum);

      dor = (TextView) findViewById(R.id.details_date_of_registration);
      String DOR = i.getStringExtra("DateOFRegistration");
      dor.setText(DOR);

      otv = (TextView) findViewById(R.id.details_otv_number);
      String OTV = i.getStringExtra("OTV");
      otv.setText(OTV);

      por = (TextView) findViewById(R.id.details_place_of_registration);
      String POR = i.getStringExtra("PlaceOfRegistration");
      por.setText(POR);

      make = (TextView) findViewById(R.id.details_make);
      String Make = i.getStringExtra("Make");
      make.setText(Make);

      mm = (TextView) findViewById(R.id.details_manufacture);
      String MM = i.getStringExtra("ManufactureModel");
      mm.setText(MM);

      coo = (TextView) findViewById(R.id.details_country_of_origin);
      String COO = i.getStringExtra("CountryOfOrigin");
      coo.setText(COO);

      TextView fees = (TextView) findViewById(R.id.details_fees_classification);
      String FEES = i.getStringExtra("FeesClassification");
      fees.setText(FEES);


      TextView power = (TextView) findViewById(R.id.details_power);
      String POWER = i.getStringExtra("Power");
      power.setText(POWER);


      TextView body = (TextView) findViewById(R.id.details_body_description);
      String BODY = i.getStringExtra("BodyDescription");
      body.setText(BODY);

      TextView color = (TextView) findViewById(R.id.details_color);
      String COLOR = i.getStringExtra("Color");
      color.setText(COLOR);


      TextView yom = (TextView) findViewById(R.id.details_year_of_maufacture);
      String YOM = i.getStringExtra("YearOfManufucture");
      yom.setText(YOM);

      TextView cov = (TextView) findViewById(R.id.details_classification_of_vehicle);
      String COV = i.getStringExtra("ClassificationOfVehicle");
      cov.setText(COV);

      TextView fuel = (TextView) findViewById(R.id.details_fuel);
      String FUEL = i.getStringExtra("Fuel");
      fuel.setText(FUEL);

      TextView EngineNum = (TextView) findViewById(R.id.details_engine_number);
      String ENGINENUM = i.getStringExtra("EngineNumber");
      EngineNum.setText(ENGINENUM);

      TextView ChassisNum = (TextView) findViewById(R.id.details_chassis_number);
      String CHASISNUM = i.getStringExtra("ChassisNumber");
      ChassisNum.setText(CHASISNUM);


      TextView Netweight = (TextView) findViewById(R.id.details_netweihgt);
      String NETWEIGHT = i.getStringExtra("NetWeihgt");
      Netweight.setText(NETWEIGHT);

      TextView GrossWeight = (TextView) findViewById(R.id.details_gross_weight);
      String GROSSWEIGHT = i.getStringExtra("GrossWeight");
      GrossWeight.setText(GROSSWEIGHT);

      TextView details_weight_units = (TextView) findViewById(R.id.details_weight_units);
      String WEIGHTUNITS = i.getStringExtra("WeightUnits");
      details_weight_units.setText(WEIGHTUNITS);

      TextView details_size_of_tyres = (TextView) findViewById(R.id.details_size_of_tyres);
      String SIZEOFTYRES = i.getStringExtra("SizeOfTyres");
      details_size_of_tyres.setText(SIZEOFTYRES);

      TextView details_number_of_wheels = (TextView) findViewById(R.id.details_number_of_wheels);
      String NOW = i.getStringExtra("NumberOfWheels");
      details_number_of_wheels.setText(NOW);

      TextView noa = (TextView) findViewById(R.id.details_number_of_axles);
      String NOA = i.getStringExtra("NumberOfAxles");
      noa.setText(NOA);


      TextView details_seating_capacity = (TextView) findViewById(R.id.details_seating_capacity);
      String SEAT = i.getStringExtra("SeatingCapacity");
      details_seating_capacity.setText(SEAT);

      TextView details_purpose = (TextView) findViewById(R.id.details_purpose);
      String PUR = i.getStringExtra("Purpose");
      details_purpose.setText(PUR);

      TextView details_category_of_ownership = (TextView) findViewById(R.id.details_category_of_ownership);
      String CAT_OWNER = i.getStringExtra("CategoryOfOwnership");
      details_category_of_ownership.setText(CAT_OWNER);

      TextView details_tax_category = (TextView) findViewById(R.id.details_tax_category);
      String TAX_OWNER = i.getStringExtra("TaxCategory");
      details_tax_category.setText(TAX_OWNER);

      TextView details_attachments = (TextView) findViewById(R.id.details_attachments);
      String Attachments = i.getStringExtra("Attachments");
      details_attachments.setText(Attachments);

      TextView details_endorsements = (TextView) findViewById(R.id.details_endorsements);
      String Endorsements = i.getStringExtra("Endorsements");
      details_endorsements.setText(Endorsements);

      TextView details_issue_date = (TextView) findViewById(R.id.details_issue_date);
      String IssueDate = i.getStringExtra("IssueDate");
      details_issue_date.setText(IssueDate);

      TextView details_remarks = (TextView) findViewById(R.id.details_remarks);
      String Remarks = i.getStringExtra("Remarks");
      details_remarks.setText(Remarks);

      TextView details_names = (TextView) findViewById(R.id.details_names);
      String Name = i.getStringExtra("Name");
      details_names.setText(Name);


      TextView details_tin = (TextView) findViewById(R.id.details_tin);
      String Tin = i.getStringExtra("Tin");
      details_tin.setText(Tin);

      TextView details_district = (TextView) findViewById(R.id.details_district);
      String District = i.getStringExtra("District");
      details_district.setText(District);

      TextView details_country = (TextView) findViewById(R.id.details_country);
      String Country = i.getStringExtra("Country");
      details_country.setText(Country);

      TextView details_postal_addres = (TextView) findViewById(R.id.details_postal_addres);
      String PostalAddress = i.getStringExtra("PostalAddress");
      details_postal_addres.setText(PostalAddress);

      TextView details_name_of_signatory = (TextView) findViewById(R.id.details_name_of_signatory);
      String NameOfSignatory = i.getStringExtra("NameOfSignatory");
      details_name_of_signatory.setText(NameOfSignatory);


     }



    }


 @Override
 public boolean onOptionsItemSelected(MenuItem item) {
  switch (item.getItemId()) {

   case android.R.id.home:
    onBackPressed();
    break;


  }
  return super.onOptionsItemSelected(item);
 }
}
