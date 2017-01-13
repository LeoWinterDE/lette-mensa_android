package de.lette.mensaplan.app;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

// Ask Marcel
public class UpdateSpeisen extends AsyncTask<Void, Void, Void> {
	private Context mCon;
	private LocalTagesplan tagesplan;
	private ProgressDialog progressDialog;

	public UpdateSpeisen(Context con) {
		mCon = con;
	}

	@Override
	protected void onPreExecute() {
		progressDialog = new ProgressDialog(mCon);
		progressDialog.setTitle("Update Speisen");
		progressDialog.setMessage("Speisen werden geladen");
		progressDialog.show();
	}

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

	@Override
	protected void onPostExecute(Void params) {
		// Give some feedback on the UI.
		progressDialog.dismiss();
		Toast.makeText(mCon, "Speisen fertig geladen!", Toast.LENGTH_LONG).show();
	}

}