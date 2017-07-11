package company.override.huzykamz.policecopuganda;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class RegistrationTabs extends AppCompatActivity {



    private RecyclerView mRecyclerview;
    private ProgressDialog mDialog;
    public Context c;
    final static Context cn= null;
    private   static  String eventname="";
    private static String EventName= "";
    private static final int MAX_WIDTH = 1024;
    private static final int MAX_HEIGHT = 768;
    private String title_eve;
    private String pic_;
    private ImageView image;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    private int[] tabIcons = {
            R.drawable.birth_reg,
            R.drawable.death_reg,

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_tabs);


        Intent inn = getIntent();
        title_eve = inn.getStringExtra("CompName");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_registration);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_registration_txt);
        mTitle.setText("Birth and Death Registration");


        //      image =(ImageView) findViewById(R.id.image_scroll);
        setSupportActionBar(toolbar);



        getSupportActionBar().setDisplayShowHomeEnabled(true);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");



        viewPager = (ViewPager) findViewById(R.id.viewpager_registration);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs_registration);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

    }



    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);



    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new BirthRegistrationFragment(), "Birth Registration");
        adapter.addFragment(new DeathRegistrationFragment(), "Death Persons");


        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
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
