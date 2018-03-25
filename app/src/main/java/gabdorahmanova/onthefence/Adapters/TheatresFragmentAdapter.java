package gabdorahmanova.onthefence.Adapters;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import gabdorahmanova.onthefence.Fragments.FavouritesFragment;
import gabdorahmanova.onthefence.Fragments.NewsFragment;
import gabdorahmanova.onthefence.Helpers.OnSwipeTouchListener;
import gabdorahmanova.onthefence.R;
import gabdorahmanova.onthefence.Activities.TheatreActivity;
import gabdorahmanova.onthefence.Units.Theatre;

/**
 * Created by admin on 07.02.2018.
 */

public class TheatresFragmentAdapter extends RecyclerView.Adapter<TheatresFragmentAdapter.ViewHolder> {






    public class ViewHolder extends RecyclerView.ViewHolder {



        CardView cv;
        TextView theatreName,helper;
        SimpleDraweeView theatrePhoto;

        CardViewClickListener cvListener = new CardViewClickListener();
        public ViewHolder(final View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            theatreName = itemView.findViewById(R.id.person_name);
            theatrePhoto = itemView.findViewById(R.id.person_photo);
            helper = itemView.findViewById(R.id.helper);
            cv.setOnClickListener(cvListener);
        }
    }

    String way;
    class CardViewClickListener implements View.OnClickListener {


        int pos;
        String name;
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, TheatreActivity.class);

            if (way.equals("favourites")){
                intent.putExtra(way,new String[]{String.valueOf(pos),name});
            }
            else{
                intent.putExtra(way,pos);
            }
            context.startActivity(intent);

        }


        void setRecord(int pos,String name) {

            this.pos = pos;
            this.name = name;
        }
    }


    private ArrayList<Theatre> persons;
    private Context context;
    public TheatresFragmentAdapter(ArrayList<Theatre> persons, Context context,String way){
        this.persons = persons;
        this.context = context;
        this.way = way;


    }




    @Override
    public TheatresFragmentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                 int viewType) {


        View itemView = LayoutInflater.from(
                parent.getContext()).inflate(R.layout.listitem, parent, false);

        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder,  int position) {

        final Theatre theater = persons.get(position);
        holder.theatreName.setText(theater.getName());




            new Thread(new Runnable() {//новый поток для работы с сетью. Иначе рабоать не будет!
                @Override
                public void run() {

                        ((Activity) context).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                holder.theatrePhoto.setImageURI(Uri.parse(theater.getPiclink()));
                                holder.helper.setVisibility(View.INVISIBLE);

                            }
                        });

                }
            }).start();



        holder.cvListener.setRecord(position, theater.getName());



    }


    @Override
    public int getItemCount() {
        return persons.size();
    }
}