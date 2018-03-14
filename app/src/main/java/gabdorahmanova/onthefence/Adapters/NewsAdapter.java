package gabdorahmanova.onthefence.Adapters;

import android.app.Activity;
import android.content.Context;
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


        if (pv.getPicture() == null) {// проверяем есть у нас сохранёная картинка, если нет, скачиваем и сохраняем в память

            new Thread(new Runnable() {//новый поток для работы с сетью. Иначе рабоать не будет!
                @Override
                public void run() {
                    try {
                        URL url = new URL(pv.getLink());
                        final Bitmap pic = BitmapFactory.decodeStream(url.openConnection().getInputStream()); // полчаем картинку по ссылке
                        ((Activity) context).runOnUiThread(new Runnable() { // с визуальными элментами можем работать только в главном потоке! Тут нам помогает контект.
                            @Override
                            public void run() {
                                pv.setPicture(pic); // сохраяем картинку, чтобы при повторном проистовании не загружать снова
                                holder.picture.setImageBitmap(pic);                         }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();


        } else {
            holder.picture.setImageBitmap(pv.getPicture());
        }
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
        ImageView picture;
        ClickListener cvListener = new ClickListener();

        public ViewHolder(View itemView) {
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

