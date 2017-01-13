package de.lette.mensaplan.app;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * Speisen werden im aktualisiert.
 * 
 * @author Marcel Henze
 */

public class UpdateSpeisen extends AsyncTask<Void, Void, Void> {
	private Context mCon;
	private LocalTagesplan tagesplan;
	private ProgressDialog progressDialog;

	public UpdateSpeisen(Context con) {
		mCon = con;
	}

	/**
	 * Bevor der Update prozess beginnt wird ein Dialogfeld angelegt, welches den user darüber informiert, was gerade geschieht.
	 */
	
	@Override
	protected void onPreExecute() {
		progressDialog = new ProgressDialog(mCon);
		progressDialog.setTitle("Update Speisen");
		progressDialog.setMessage("Speisen werden geladen");
		progressDialog.show();
	}

	/**
	 * Führt im Hintergrund den Update prozess durch.
	 */
	
	@Override
	protected Void doInBackground(Void... params) {
		try {
			// Set a time to simulate a long update process.
			Thread.sleep(4000);
			tagesplan.setLocalTagesplan();
			return null;

		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Nach dem beenden des Updates wird das Dialogfeld geschlossen.
	 */
	
	@Override
	protected void onPostExecute(Void params) {
		// Give some feedback on the UI.
		progressDialog.dismiss();
		Toast.makeText(mCon, "Speisen fertig geladen!", Toast.LENGTH_LONG).show();
	}

}