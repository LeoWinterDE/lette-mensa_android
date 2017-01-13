package de.lette_verein.lettemensaspeiseplan.gfx;

import android.content.Context;
import android.widget.DatePicker;

import de.lette_verein.lettemensaspeiseplan.management.SimpleDate;

/**
 * Created by NikolaiJay on 26.05.2016.
 */
public class SpeiseDatepicker extends DatePicker {

    public static SimpleDate selectedDate;


    public SpeiseDatepicker(Context context, final CalenderUsed calenderUsed) {
        super(context);

        OnDateChangedListener onDateChangedListener = new OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                selectedDate = new SimpleDate(getDayOfMonth(),getMonth() + 1,getYear());
                calenderUsed.handle();
            }
        };

        selectedDate = new SimpleDate(getDayOfMonth(),getMonth() + 1,getYear());
        init(getYear(),getMonth(),getDayOfMonth(),onDateChangedListener);
    }


    @Override
    public void init(int year, int monthOfYear, int dayOfMonth, OnDateChangedListener onDateChangedListener) {
        super.init(year, monthOfYear, dayOfMonth, onDateChangedListener);
    }

    public static SimpleDate getSelectedDate() {
        return selectedDate;
    }

    public interface CalenderUsed{
        void handle();
    }
}
