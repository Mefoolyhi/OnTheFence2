package gabdorahmanova.onthefence.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import gabdorahmanova.onthefence.R;
import gabdorahmanova.onthefence.Activities.TheatreActivity;
import gabdorahmanova.onthefence.Units.Theatre;

/**
 * Created by admin on 07.02.2018.
 */

public class TheatresFragmentAdapter extends RecyclerView.Adapter<TheatresFragmentAdapter.ViewHolder> {






    public class ViewHolder extends RecyclerView.ViewHolder {



        CardView cv;
        TextView theatreName;
        ImageView theatrePhoto;

        CardViewClickListener cvListener = new CardViewClickListener();
        ViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            theatreName = itemView.findViewById(R.id.person_name);
            theatrePhoto = itemView.findViewById(R.id.person_photo);
            cv.setOnClickListener(cvListener);
        }
    }

    class CardViewClickListener implements View.OnClickListener {


        private Theatre theatre;
        int pos;
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, TheatreActivity.class);
            intent.putExtra("theatre",pos);
            context.startActivity(intent);

        }


        void setRecord(Theatre theatre,int pos) {
            this.theatre = theatre;
            this.pos = pos;
        }
    }


    private ArrayList<Theatre> persons;
    private Context context;
    public TheatresFragmentAdapter(ArrayList<Theatre> persons, Context context){
        this.persons = persons;
        this.context = context;

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

    @Override
    public void onBindViewHolder(final ViewHolder holder,  int position) {

        final Theatre theater = persons.get(position);
        holder.theatreName.setText(theater.getName());


        if (theater.getPicture() == null) {// проверяем есть у нас сохранёная картинка, если нет, скачиваем и сохраняем в память

            new Thread(new Runnable() {//новый поток для работы с сетью. Иначе рабоать не будет!
                @Override
                public void run() {
                    try {
                        URL url = new URL(theater.getPiclink());
                        final Bitmap pic = BitmapFactory.decodeStream(url.openConnection().getInputStream()); // полчаем картинку по ссылке
                        ((Activity) context).runOnUiThread(new Runnable() { // с визуальными элментами можем работать только в главном потоке! Тут нам помогает контект.
                            @Override
                            public void run() {
                                theater.setPicture(pic); // сохраяем картинку, чтобы при повторном проистовании не загружать снова
                                holder.theatrePhoto.setImageBitmap(pic);                           }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();


        } else {
            holder.theatrePhoto.setImageBitmap(theater.getPicture());
        }
        holder.cvListener.setRecord(theater,position);



    }


    @Override
    public int getItemCount() {
        return persons.size();
    }
}