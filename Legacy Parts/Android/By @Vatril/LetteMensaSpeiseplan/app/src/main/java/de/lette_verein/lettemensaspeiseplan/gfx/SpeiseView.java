package de.lette_verein.lettemensaspeiseplan.gfx;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import de.lette_verein.lettemensaspeiseplan.management.Speise;

/**
 * Created by NikolaiJay on 28.05.2016.
 */
public class SpeiseView extends LinearLayout {
    public SpeiseView(Context context, Speise speise) {
        super(context);


        TextView name = new TextView(context);
        TextView price = new TextView(context);

        name.setText(speise.get_name());
        price.setText(speise.getPreisInEuro());

        addView(name);
    }
}
