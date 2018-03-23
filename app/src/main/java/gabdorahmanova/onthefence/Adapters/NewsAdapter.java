package gabdorahmanova.onthefence.Adapters;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import gabdorahmanova.onthefence.Activities.MainActivity;
import gabdorahmanova.onthefence.Fragments.NewsFragment;
import gabdorahmanova.onthefence.Fragments.TheatresFragment;
import gabdorahmanova.onthefence.Helpers.OnSwipeTouchListener;
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
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final PostValue pv = data.get(position);
        holder.time.setText(pv.getTime());
        holder.heading.setText(pv.getHeading());
        holder.picture.setImageURI(pv.getLink());
        holder.cvListener.setRecord(pv,position);




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
        SimpleDraweeView picture;
        ClickListener cvListener = new ClickListener();

        public ViewHolder(final View itemView) {
            super(itemView);
            picture = itemView.findViewById(R.id.pic_news);
            cv = itemView.findViewById(R.id.cv);
            time = itemView.findViewById(R.id.time);
            heading = itemView.findViewById(R.id.heading);
            cv.setOnClickListener(cvListener);

        }}
        class ClickListener implements View.OnClickListener{

            PostValue pv;
            int pos;

            @Override
            public void onClick(View v) {

            }

            void setRecord(PostValue pv,int pos){
                this.pv = pv;
                this.pos = pos;
            }

        }


    }

