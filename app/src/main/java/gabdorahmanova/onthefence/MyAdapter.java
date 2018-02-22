package gabdorahmanova.onthefence;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by admin on 07.02.2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {






    public class ViewHolder extends RecyclerView.ViewHolder {



        CardView cv;
        TextView personName;
        ImageView personPhoto;

        CardViewClickListener cvListener = new CardViewClickListener();
        ViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            personName = itemView.findViewById(R.id.person_name);
            personPhoto = itemView.findViewById(R.id.person_photo);
            cv.setOnClickListener(cvListener);
        }
    }

    class CardViewClickListener implements View.OnClickListener {


        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, TheatreActivity.class);
            context.startActivity(intent);

        }
    }


    ArrayList<String> persons;
    private Context context;
    MyAdapter(ArrayList<String> persons, Context context){
        this.persons = persons;
        this.context = context;

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }



    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listitem, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.personName.setText(persons.get(position));
        new Thread(new Runnable() {//новый поток для работы с сетью. Иначе рабоать не будет!
            @Override
            public void run() {
                try {
                    URL url = new URL(TheatresFragment.links.get(position));
                    final Bitmap poster = BitmapFactory.decodeStream(url.openConnection().getInputStream()); // полчаем картинку по ссылке
                    ((Activity) context).runOnUiThread(new Runnable() { // с визуальными элментами можем работать только в главном потоке! Тут нам помогает контект.
                        @Override
                        public void run() {
                            
                            holder.personPhoto.setImageBitmap(poster); //отображаем картинку
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();




    }


    @Override
    public int getItemCount() {
        return persons.size();
    }
}