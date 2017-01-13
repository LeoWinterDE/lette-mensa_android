package de.lette.mensaplan.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import de.lette.mensaplan.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Zusatzstoffe View sollte eigentlich auch halbwegs fertig sein.
 * Wird angezeigt wenn man im Drawer auf Zusatzstoffe clickt.
 */
public class ZusaetzeFragment extends Fragment {

	public static ZusaetzeFragment newInstance(int page) {
		ZusaetzeFragment fragment = new ZusaetzeFragment();
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_zusaetze, container, false);

		InputStream zusätze = getResources().openRawResource(R.raw.zusaetze);
		InputStream allergene = getResources().openRawResource(R.raw.allergene);

		TextView zusatzView = (TextView) view.findViewById(R.id.zusaetze);
		TextView allergenView = (TextView) view.findViewById(R.id.allergene);

		zusatzView.setText(readFile(zusätze));
		allergenView.setText(readFile(allergene));

		return view;
	}

	private StringBuilder readFile(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder strBuild = new StringBuilder();
		String line;
		try {
			while ((line = reader.readLine()) != null) {
				strBuild.append(line);
				strBuild.append("\n");
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return strBuild;
	}
}