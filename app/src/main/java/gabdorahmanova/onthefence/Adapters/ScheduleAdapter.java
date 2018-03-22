package gabdorahmanova.onthefence.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import gabdorahmanova.onthefence.R;
import gabdorahmanova.onthefence.Units.Performance;

/**
 * Created by admin on 20.03.2018.
 */

public class ScheduleAdapter extends ArrayAdapter<Performance> {


    public ScheduleAdapter(Context context, List<Performance> objects) {
        super(context, R.layout.schedule_list_item, objects);
        this.data = objects;
        this.context = context;
    }

    Context context;
    List<Performance> data;


    @Override
    public View getView(int position, View contentView, ViewGroup parent) {




        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.schedule_list_item, parent, false);
        Performance p = getItem(position);
        TextView name = view.findViewById(R.id.name);
        name.setText(p.getName());
        TextView cost = view.findViewById(R.id.cost);
        cost.setText(p.getCost());

        TextView time = view.findViewById(R.id.time);
        time.setText(p.getTime());

        TextView type = view.findViewById(R.id.type);
        type.setText(p.getType());


        return view;
    }
}