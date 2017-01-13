package de.lette.mensaplan.app;

import java.util.Calendar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.provider.Settings.Secure;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import de.lette.mensaplan.R;

public class MainActivity extends AppCompatActivity implements NavigationDrawerCallbacks {

	private Toolbar mToolbar;
	private NavigationDrawerFragment mNavigationDrawerFragment;
	private SharedPreferences prefs;
	private static final String FIRST_LAUNCH = "first_launch";
	public static final String ARG_WOCHE = "ARG_WOCHE";
	public static String android_id;
	private boolean isLast = false;
	private LocalTagesplan tagesplan;
	private Menu menu;

	/**
	 * Appstart
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// Tagesplan holen //
		tagesplan = new LocalTagesplan(getApplicationContext());
		// Android Geräte Id holen (WICHTIG für die Bewertung)
		android_id = Secure.getString(getContentResolver(),Secure.ANDROID_ID);
		Log.d("Android_ID: ", android_id);
		// local persistent Storage holen
		prefs = PreferenceManager.getDefaultSharedPreferences(this);

		// local persistent Storage auswerten
		// Wenn die App zum ersten mal gestartet wurde:
		if (prefs.getBoolean(FIRST_LAUNCH, true)) {
			// Zeige die FirstStartActivity mit der Begrüßung
			Intent intent = new Intent(this, FirstLaunch.class);
			startActivity(intent);
		}

		// Soll eigentlich den zuletzt heruntergeladenen Tagesplan aus dem local Storage laden und anzeigen falls keine Internetverbindung besteht, hier fehlt nur noch der Check auf eine Internetverbindung.
//		if (prefs.getString("Tagesplan", null) == null) {
			new Thread() {
				public void run() {
					tagesplan.setLocalTagesplan();
				}
			}.start();
			Log.i("Neuer Tagesplan", "Neuer Tagesplan geladen");
//		}
		// Zeug für das Wochenmanagement
		Calendar now = Calendar.getInstance();
		int mWoche = now.get(Calendar.WEEK_OF_MONTH);

		// Wichtig für den Drawer, do not change!
		mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
		setSupportActionBar(mToolbar);
		getSupportActionBar().setDisplayShowHomeEnabled(true);

		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.fragment_drawer);
		mNavigationDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);

		// Schließe Drawer
		mNavigationDrawerFragment.closeDrawer();
		if (mWoche <= 4) {
			mNavigationDrawerFragment.selectItem(mWoche - 2);
		} else {
			mNavigationDrawerFragment.selectItem(3);
		}
	}
	
	/**
	 * Menü: R.menu.menu wird registriert. (hardwarebutton usw)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		this.menu = menu;
		return true;
	}

	/**
	 * Dont know ask Marcel
	 */
	public void resetUpdating() {
		// Get our refresh item from the menu
		MenuItem m = menu.findItem(R.id.action_refresh);
		if (m.getActionView() != null) {
			// Remove the animation.
			m.getActionView().clearAnimation();
			m.setActionView(null);
		}
	}

	/**
	 * Vermutlich: Reagiert auf den Click auf den Option-Knopf (Der Aktualisieren Knopf oben recht in der Menüleiste)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		new UpdateSpeisen(this).execute();
		return super.onOptionsItemSelected(item);
	}

	/**
	 * Reagiert wenn der Benutzer auf ein Item im Drawer clickt.
	 * Der Drawer ist das Menu was an der Seite rausfährt.
	 * Ein Item im Drawer ist in unserem Fall eine Woche bzw. Impressum.
	 */
	@Override
	public void onNavigationDrawerItemSelected(int position) {
		Fragment fragment = null;
		final FragmentTransaction fm = getSupportFragmentManager().beginTransaction();
		switch (position) {
		// case 0-3 (inclusive) bearbeiten die Clicks für die erste bis vierte Woche.
		case 0:
			fragment = TabFragment.newInstance(position);
			break;
		case 1:
			fragment = TabFragment.newInstance(position);
			break;
		case 2:
			fragment = TabFragment.newInstance(position);
			break;
		case 3:
			fragment = TabFragment.newInstance(position);
			break;
		// case 4 bearbeitet den Click für das Item "Zusätze"
		case 4:
			fragment = ZusaetzeFragment.newInstance(position);
			break;
		// case 4 bearbeitet den Click für das Item "Impressum"
		case 5:
			fragment = ImpressFragment.newInstance(position);
			break;
		default:
			break;
		}
		// Der nachfolgende Code dient zur Funktionserweiterung der Zurücktaste.
		// TODO: Apdapterkompatibilität prüfen, vlt kann man hier etwas besser machen.
		isLast = false;
		mNavigationDrawerFragment.getAdapter().selectPosition(position);
		fm.replace(R.id.fragment_container, fragment).addToBackStack("fragBack");
		if (fragment != null) {
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					fm.commitAllowingStateLoss();
				}
			}, 350);
		}
	}

	/**
	 * Reagiert wenn der Benutzer die Zurück-Taste auf dem Smartphone betätigt.
	 * Im Moment funktioniert der Abgleich mit dem Drawer noch nicht.
	 * TODO: Drawer nach dem Drücken der Zurück-Taste updaten.
	 */
	@Override
	public void onBackPressed() {
		if (mNavigationDrawerFragment.isDrawerOpen()) {
			mNavigationDrawerFragment.closeDrawer();
		} else {
			if (!isLast) {
				if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
					super.onBackPressed();
				} else if (getSupportFragmentManager().getBackStackEntryCount() <= 1) {
					Toast.makeText(this, "Durch nochmaliges drücken wird die App beendet.", Toast.LENGTH_SHORT).show();
					isLast = true;
				}
			} else if (isLast) {
				finish();
			}
		}
	}
}
