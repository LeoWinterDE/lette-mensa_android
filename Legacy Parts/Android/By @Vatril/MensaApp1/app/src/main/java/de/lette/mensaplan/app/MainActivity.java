package de.lette.mensaplan.app;

import java.util.Calendar;

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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import de.lette.mensaplan.R;

/**
 * Main Methode in der die App aufgebaut und verwaltet wird.
 * 
 * @author Marcel Henze
 *  */

public class MainActivity extends AppCompatActivity implements NavigationDrawerCallbacks {

	private Toolbar mToolbar;
	private NavigationDrawerFragment mNavigationDrawerFragment;
	private SharedPreferences prefs;
	private static final String FIRST_LAUNCH = "first_launch";
	public static final String ARG_WOCHE = "ARG_WOCHE";
	public static String android_id;
	private boolean isLast = false;
	private LocalTagesplan tagesplan;

	/**
	 * Wird start der App aufgerufen.
	 * In der android_id wird die einzigartige ID des Geräts gespeichert um die identifizierung des users im Bewertungssystem.
	 * Abfrage auf den ersten start der App bei true wird der user auf FirstLaunch umgeleitet.
	 * Die App holt sich den aktuellen Speiseplan vom server.
	 * Setup der toolbar und des drawers.
	 * Setzen der wochenauswahl auf die aktuelle Woche.
	 */
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tagesplan = new LocalTagesplan(getApplicationContext());
		android_id = Secure.getString(getContentResolver(),Secure.ANDROID_ID);
		prefs = PreferenceManager.getDefaultSharedPreferences(this);

		if (prefs.getBoolean(FIRST_LAUNCH, true)) {
			Intent intent = new Intent(this, FirstLaunch.class);
			startActivity(intent);
		}

//		if (prefs.getString("Tagesplan", null) == null) {
			new Thread() {
				public void run() {
					tagesplan.setLocalTagesplan();
				}
			}.start();
			Log.i("Neuer Tagesplan", "Neuer Tagesplan geladen");
//		}
		Calendar now = Calendar.getInstance();
		int mWoche = now.get(Calendar.WEEK_OF_MONTH);

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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * Verantwortlich für das drücken des refresh Buttons in der Menüleiste.
	 * Fordert neue Daten vom Server an.
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		new UpdateSpeisen(this).execute();
		return super.onOptionsItemSelected(item);
	}

	/**
	 * Navigation durch den Drawer.
	 * Fragente werden erstellt und ausgetauscht.
	 */
	@Override
	public void onNavigationDrawerItemSelected(int position) {
		Fragment fragment = null;
		final FragmentTransaction fm = getSupportFragmentManager().beginTransaction();
		switch (position) {
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
		case 4:
			fragment = ZusaetzeFragment.newInstance(position);
			break;
		case 5:
			fragment = ImpressFragment.newInstance(position);
			break;
		default:
			break;
		}
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
	 * Überschreiben der onBackPressed() Methode welche beim drücken des return Buttons aufgerufen wird.
	 * Wenn der Drawer geöffnet ist schließt ein drücken den Drawer.
	 * Wenn der Drawer geschlossen ist wird solange durch die fragmente zurücknavigiert, bis man wieder auf dem ersten Fragment landet.
	 * Wenn man sich auf dem ersten Fragment befindet wird man darauf aufmerksam gemacht, dass ein erneutes drücken die App beendet.
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
