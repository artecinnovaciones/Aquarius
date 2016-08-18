package com.artecinnovaciones.aquarius;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.artecinnovaciones.aquarius.adapters.ViewPagerAdapter;
import com.artecinnovaciones.aquarius.fragments.PacificosFragment;
import com.artecinnovaciones.aquarius.fragments.AgresivosFragment;

public class PecesActivity extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    private int[] tabIcons = {
            R.drawable.ic_agresivo,
            R.drawable.ic_pacifico
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peces);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tabLayout=(TabLayout)findViewById(R.id.tabLayout);
        viewPager=(ViewPager)findViewById(R.id.viewPager);

        viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragments(new AgresivosFragment(),"Agresivos");
        viewPagerAdapter.addFragments(new PacificosFragment(),"Pacíficos");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        setupTabIcons();

        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.icons));
        tabLayout.setTabTextColors(getResources().getColor(R.color.primary_light),getResources().getColor(R.color.icons));
    }

    private void setupTabIcons() {
        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.item_tabs, null);
        tabOne.setText("Pacíficos");
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_pacifico, 0, 0);
        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.item_tabs, null);
        tabTwo.setText("Agresivos");
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_agresivo, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabTwo);
    }
}
