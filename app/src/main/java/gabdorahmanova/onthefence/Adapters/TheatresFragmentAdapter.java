package gabdorahmanova.onthefence.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

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
        ImageView theatrePhoto;

        CardViewClickListener cvListener = new CardViewClickListener();
        ViewHolder(View itemView) {
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
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, TheatreActivity.class);

            intent.putExtra(way,pos);
            context.startActivity(intent);

        }


        void setRecord(int pos) {

            this.pos = pos;
        }
    }
    LruCache<String, Bitmap> mMemoryCache;


    private ArrayList<Theatre> persons;
    private Context context;
    public TheatresFragmentAdapter(ArrayList<Theatre> persons, Context context,String way){
        this.persons = persons;
        this.context = context;
        this.way = way;
        mMemoryCache = new LruCache<String, Bitmap>((int) (Runtime.getRuntime().maxMemory()) / 8) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getByteCount();
            }
        };

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }



    @Override
    public TheatresFragmentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                 int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listitem, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    Bitmap getBitmapFromMemCache(String key) {
        return mMemoryCache.get(key);
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder,  int position) {

        final Theatre theater = persons.get(position);
        holder.theatreName.setText(theater.getName());


        if (theater.getPicture() == null) {

            new Thread(new Runnable() {//новый поток для работы с сетью. Иначе рабоать не будет!
                @Override
                public void run() {
                    try {
                        URL url = new URL(theater.getPiclink());
                        Bitmap pic = getBitmapFromMemCache(url.toString());
                        if (pic == null) {
                            pic = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                            addBitmapToMemoryCache(url.toString(),pic);
                        }

                        final Bitmap finalPic = pic;
                        ((Activity) context).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                theater.setPicture(finalPic);
                                holder.helper.setVisibility(View.INVISIBLE);
                                holder.theatrePhoto.setImageBitmap(finalPic);

                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();


        } else {
            holder.theatrePhoto.setImageBitmap(theater.getPicture());
        }
        holder.cvListener.setRecord(position);



    }


    @Override
    public int getItemCount() {
        return persons.size();
    }
}