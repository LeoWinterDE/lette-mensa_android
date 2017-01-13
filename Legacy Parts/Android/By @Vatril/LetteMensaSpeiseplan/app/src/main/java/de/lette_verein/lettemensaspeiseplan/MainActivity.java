package de.lette_verein.lettemensaspeiseplan;

import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridLayout;
import android.widget.PopupWindow;

import de.lette_verein.lettemensaspeiseplan.gfx.SpeiseDatepicker;
import de.lette_verein.lettemensaspeiseplan.gfx.SpeiseListFiller;
import de.lette_verein.lettemensaspeiseplan.gfx.Speiselist;
import de.lette_verein.lettemensaspeiseplan.management.Datamanager;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private SpeiseDatepicker calender;
    private PopupWindow popup;
    private GridLayout container;
    private Speiselist plan;
    private Speiselist diatplan;
    private Speiselist allSpeisen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Datamanager.init(this );
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitle("Mensa Speiseplan");

        container = (GridLayout) findViewById(R.id.gridLayout);





        popup = new PopupWindow();

        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);

        calender = new SpeiseDatepicker(this, new SpeiseDatepicker.CalenderUsed() {
            @Override
            public void handle() {
                popup.dismiss();
                plan.udateToDate();
                diatplan.udateToDate();
            }
        });

        plan = new Speiselist(this, SpeiseListFiller.ART.NORMAL);
        diatplan = new Speiselist(this,SpeiseListFiller.ART.DIAT);
        allSpeisen = new Speiselist(this,SpeiseListFiller.ART.ALLSPEISEN);

        container.addView(plan);

        popup = new PopupWindow(calender,(int)(size.x*0.8),(int)(size.y * 0.63));
        popup.setOutsideTouchable(true);
        popup.setTouchable(true);
        popup.setBackgroundDrawable(new ColorDrawable());

        calender.setBackgroundColor(Color.WHITE);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!popup.isShowing()){
                    popup.showAtLocation(view, Gravity.VERTICAL_GRAVITY_MASK, 0, 0);
                }
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
            //return true;
        //}

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.plan){
            container.removeAllViews();
            container.addView(plan);
        }
        if (id == R.id.diatplan){
            container.removeAllViews();
            container.addView(diatplan);
        }
        if(id == R.id.speisen){
            container.removeAllViews();
            container.addView(allSpeisen);
        }
        if (id == R.id.settings){}
        if (id == R.id.help){}

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
