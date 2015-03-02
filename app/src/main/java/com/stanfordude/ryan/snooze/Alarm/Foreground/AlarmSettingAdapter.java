package com.stanfordude.ryan.snooze.Alarm.Foreground;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.stanfordude.ryan.snooze.Alarm.Foreground.AlarmSetting;
import com.stanfordude.ryan.snooze.R;

import java.util.ArrayList;

/**
 * Created by ryan on 1/28/15.
 */
public class AlarmSettingAdapter extends ArrayAdapter<AlarmSetting> {
    private View listItem;
    private LayoutInflater inflater;
    private final Context context;
    private final ArrayList<AlarmSetting> alarmSettingList;
    private TextView time, state;
    private ToggleButton on;
    private AlarmSetting temp;

    public AlarmSettingAdapter(Context context, ArrayList<AlarmSetting> list) {
        super(context, R.layout.alarm_list_item, list);
        this.context = context;
        this.alarmSettingList = list;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        listItem = inflater.inflate(R.layout.alarm_list_item, parent, false);
        time = (TextView) listItem.findViewById(R.id.alarm_list_item_time);
        state = (TextView) listItem.findViewById(R.id.alarm_list_item_state);
        on = (ToggleButton) listItem.findViewById(R.id.alarm_list_item_toggleButton);


        temp = alarmSettingList.get(position);
        time.setText(temp.getTime());
        state.setText(temp.getState());
        //if(convertView!=null)
        on.setChecked(temp.isSetOn());
        temp.setSetOn(on.isChecked());
        on.setTag(position);
        on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (int) v.getTag();
 //               if(((ToggleButton)v).isChecked()!=alarmSettingList.get(position).isSetOn())
                    if(((ToggleButton)v).isChecked()) alarmSettingList.get(position).setAlarm();
                    else  alarmSettingList.get(position).cancelAlarm();


                alarmSettingList.get(position).setSetOn(((ToggleButton) v).isChecked());
             //   boolean onOrOff= ((ToggleButton)v).isChecked();
               // if(onOrOff) alarmSettingList.get(position).setAlarm();

            }
        });


        //System.out.println("\n\n\n\n "+time+" "+temp+" "+state+" "+ listItem+" ");
        return listItem;

    }

    @Override
    public int getCount() {

        return alarmSettingList.size();
    }


}
