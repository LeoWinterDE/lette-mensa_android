package de.lette.mensaplan.app;

import de.lette.mensaplan.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Fragment für die Anzeige des Impressums.
 * 
 * @author Marcel Henze
 */

public class ImpressFragment extends Fragment {

	public static ImpressFragment newInstance(int page) {
		ImpressFragment fragment = new ImpressFragment();
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	/**
	 * Baut ein Fragment aus der fragment_impress xml, gibt dem Textfeld die möglichkeit zu scrollen.
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_impress, container, false);
		TextView impressum = (TextView) view.findViewById(R.id.impressum);
		impressum.setMovementMethod(LinkMovementMethod.getInstance());
		return view;
	}
}
