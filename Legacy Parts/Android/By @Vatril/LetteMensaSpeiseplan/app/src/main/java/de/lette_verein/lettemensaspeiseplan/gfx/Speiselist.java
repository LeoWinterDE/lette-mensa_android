package de.lette_verein.lettemensaspeiseplan.gfx;

import android.content.Context;
import android.widget.ListView;

/**
 * Created by NikolaiJay on 27.05.2016.
 */
public class Speiselist extends ListView {

    private SpeiseListFiller.ART art;
    private Context context;

    public Speiselist(Context context,SpeiseListFiller.ART art) {
        super(context);
        this.art = art;
        this.context = context;
        udateToDate();
    }

    public void udateToDate(){
    setAdapter(new SpeiseListFiller(context, art));
    }


}
