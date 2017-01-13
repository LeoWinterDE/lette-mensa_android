package de.lette_verein.lettemensaspeiseplan.gfx;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import de.lette_verein.lettemensaspeiseplan.gfx.SpeiseDatepicker;
import de.lette_verein.lettemensaspeiseplan.management.Datamanager;
import de.lette_verein.lettemensaspeiseplan.management.Speise;
import de.lette_verein.lettemensaspeiseplan.management.Tagesplan;

/**
 * Created by NikolaiJay on 28.05.2016.
 */
public class SpeiseListFiller extends ArrayAdapter {


    public SpeiseListFiller(Context context, ART art) {
        super(context,android.R.layout.simple_list_item_1);



        if (art == ART.DIAT){

            Tagesplan tagesplan = Datamanager.getTagesplanByDate(SpeiseDatepicker.getSelectedDate());

            if (tagesplan != null){
                tagesplan.sort();
                for (Speise speise:tagesplan.getSpeisen()){
                    add(new SpeiseView(context,speise));
                }
            }

        }else if(art == ART.NORMAL){
            add("test");

        }else if (art == ART.ALLSPEISEN) {

        }


    }

    public enum ART{NORMAL,DIAT,ALLSPEISEN;}
}
