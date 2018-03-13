package gabdorahmanova.onthefence.Adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import gabdorahmanova.onthefence.Units.PostValue;
import gabdorahmanova.onthefence.R;

/**
 * Created by admin on 08.03.2018.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private Context context;

private ArrayList<PostValue> data;


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_list_tem, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PostValue pv = data.get(position);



    }

    public NewsAdapter(ArrayList<PostValue> data,Context context){
        this.data = data;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView time,heading;
        ClickListener cvListener = new ClickListener();
        PostValue pv;
        int pos;
        public ViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            time = itemView.findViewById(R.id.time);
            heading = itemView.findViewById(R.id.heading);
            cv.setOnClickListener(cvListener);

        }
        class ClickListener implements View.OnClickListener{


            @Override
            public void onClick(View v) {

            }
        }
        void setRecord(PostValue pv,int pos){
            this.pv = pv;
            this.pos = pos;
        }
    }
}
