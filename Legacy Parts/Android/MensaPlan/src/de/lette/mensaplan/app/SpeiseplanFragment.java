package de.lette.mensaplan.app;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import de.lette.mensaplan.R;
import de.lette.mensaplan.server.SpeiseArt;

// In this case, the fragment displays simple text based on the page
// Please change this to Adapter
public class SpeiseplanFragment extends Fragment {
	public static final String ARG_PAGE = "ARG_PAGE";
	public static final String ARG_WOCHE = "ARG_WOCHE";
	private int mPage;
	private int mWoche;
	boolean isActive = true;
	private List<Tagesplan> data;
	private LocalTagesplan tagesplan;

	public static SpeiseplanFragment newInstance(int page, int woche) {
		Bundle args = new Bundle();
		args.putInt(ARG_PAGE, page);
		args.putInt(ARG_WOCHE, woche);
		SpeiseplanFragment fragment = new SpeiseplanFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mPage = getArguments().getInt(ARG_PAGE);
		mWoche = getArguments().getInt(ARG_WOCHE);
		tagesplan = new LocalTagesplan(getActivity());
		data = tagesplan.getLocalTagesplan();
	}

	/**
	 * Fügt die Speisen hinzu und zeigt sie an..
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_speiseplan, container, false);

		ViewGroup vorspeisen = (LinearLayout) view.findViewById(R.id.vorspeisen);
		ViewGroup vegetarisch = (LinearLayout) view.findViewById(R.id.vegetarisch);
		ViewGroup vollkosten = (LinearLayout) view.findViewById(R.id.vollkost);
		ViewGroup beilagen = (LinearLayout) view.findViewById(R.id.beilagen);
		ViewGroup desserts = (LinearLayout) view.findViewById(R.id.dessert);

		ViewGroup diätVorspeisen = (LinearLayout) view.findViewById(R.id.diätVorspeisen);
		ViewGroup gemüseteller = (LinearLayout) view.findViewById(R.id.gemüseteller);
		ViewGroup diätVollkosten = (LinearLayout) view.findViewById(R.id.diätVollkost);
		ViewGroup diätBeilagen = (LinearLayout) view.findViewById(R.id.diätBeilagen);
		ViewGroup diätDesserts = (LinearLayout) view.findViewById(R.id.diätDessert);

		Calendar speiseDatum = Calendar.getInstance(Locale.getDefault());

		boolean hasSpeisen = false;
		for (Tagesplan tag : data) {
			speiseDatum.setTime(tag.getDatum());
			int week = speiseDatum.get(Calendar.WEEK_OF_MONTH);
			if (week != mWoche + 1)
				continue;
			int day = speiseDatum.get(Calendar.DAY_OF_WEEK);
			if (day != mPage + 1)
				continue;
			for (final Speise speise : tag.getSpeisen()) {
				ViewGroup wrap = null;
				if (speise.getArt() == SpeiseArt.VORSPEISE && !speise.isDiät()) {
					wrap = (RelativeLayout) view.findViewById(R.id.vorspeisenWrap);
					vorspeisen.addView(new SpeisenItem(getActivity().getApplicationContext(), speise, "vorspeise"));
				} else if (speise.getArt() == SpeiseArt.VEGETARISCH && !speise.isDiät()) {
					wrap = (RelativeLayout) view.findViewById(R.id.vegetarischWrap);
					vegetarisch.addView(new SpeisenItem(getActivity().getApplicationContext(), speise, "vegetarisch"));
				} else if (speise.getArt() == SpeiseArt.VOLLKOST && !speise.isDiät()) {
					wrap = (RelativeLayout) view.findViewById(R.id.vollkostWrap);
					vollkosten.addView(new SpeisenItem(getActivity().getApplicationContext(), speise, "hauptspeise"));
				} else if (speise.getArt() == SpeiseArt.BEILAGEN && !speise.isDiät()) {
					wrap = (RelativeLayout) view.findViewById(R.id.beilagenWrap);
					beilagen.addView(new SpeisenItem(getActivity().getApplicationContext(), speise, "beilagen"));
				} else if (speise.getArt() == SpeiseArt.DESSERT && !speise.isDiät()) {
					wrap = (RelativeLayout) view.findViewById(R.id.dessertWrap);
					desserts.addView(new SpeisenItem(getActivity().getApplicationContext(), speise, "dessert"));
				} else if (speise.getArt() == SpeiseArt.VORSPEISE && speise.isDiät()) {
					wrap = (RelativeLayout) view.findViewById(R.id.diätVorspeisenWrap);
					diätVorspeisen.addView(new SpeisenItem(getActivity().getApplicationContext(), speise, "vorspeise"));
				} else if (speise.getArt() == SpeiseArt.GEMÜSETELLER && speise.isDiät()) {
					wrap = (RelativeLayout) view.findViewById(R.id.gemüsetellerWrap);
					gemüseteller.addView(new SpeisenItem(getActivity().getApplicationContext(), speise, "gemueseteller"));
				} else if (speise.getArt() == SpeiseArt.LEICHTEVOLLKOST && speise.isDiät()) {
					wrap = (RelativeLayout) view.findViewById(R.id.diätVollkostWrap);
					diätVollkosten.addView(new SpeisenItem(getActivity().getApplicationContext(), speise, "hauptspeise"));
				} else if (speise.getArt() == SpeiseArt.BEILAGEN && speise.isDiät()) {
					wrap = (RelativeLayout) view.findViewById(R.id.diätBeilagenWrap);
					diätBeilagen.addView(new SpeisenItem(getActivity().getApplicationContext(), speise, "beilagen"));
				} else if (speise.getArt() == SpeiseArt.DESSERT && speise.isDiät()) {
					wrap = (RelativeLayout) view.findViewById(R.id.diätDessertWrap);
					diätDesserts.addView(new SpeisenItem(getActivity().getApplicationContext(), speise, "dessert"));
				}
				hasSpeisen = true;
				wrap.setVisibility(ViewGroup.VISIBLE);
			}
		}
		if (!hasSpeisen) {
			ViewGroup vg = (RelativeLayout) view.findViewById(R.id.keineSpeisen);
			vg.setVisibility(ViewGroup.VISIBLE);
		}
		return view;
	}

	public int getPageNumber() {
		return mPage;
	}
}