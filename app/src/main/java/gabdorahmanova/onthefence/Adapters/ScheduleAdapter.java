package gabdorahmanova.onthefence.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

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
        final View view = inflater.inflate(R.layout.schedule_list_item, parent, false);
        final Performance p = getItem(position);
        CardView cv = view.findViewById(R.id.cv);
        TextView name = view.findViewById(R.id.name);
        name.setText(p.getName());
        TextView cost = view.findViewById(R.id.cost);
        cost.setText(p.getCost());

        TextView time = view.findViewById(R.id.time);
        time.setText(p.getTime());

        TextView type = view.findViewById(R.id.type);
        type.setText(p.getType());

        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (!p.getLink().equals("")) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(p.getLink()));
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                    else{
                        Toast.makeText(context,"Свободных мест на данный спектакль нет", Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e){
                    Log.e("intent",e.getMessage());
                }
            }
        });

        return view;
    }
}